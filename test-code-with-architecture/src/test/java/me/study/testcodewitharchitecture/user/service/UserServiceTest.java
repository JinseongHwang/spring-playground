package me.study.testcodewitharchitecture.user.service;

import me.study.testcodewitharchitecture.common.domain.exception.CertificationCodeNotMatchedException;
import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import me.study.testcodewitharchitecture.mock.FakeMailSender;
import me.study.testcodewitharchitecture.mock.FakeUserRepository;
import me.study.testcodewitharchitecture.mock.TestClockHolder;
import me.study.testcodewitharchitecture.mock.TestUuidHolder;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserCreate;
import me.study.testcodewitharchitecture.user.domain.UserStatus;
import me.study.testcodewitharchitecture.user.domain.UserUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void init() {
        FakeMailSender mailSender = new FakeMailSender();
        FakeUserRepository userRepository = new FakeUserRepository();
        userRepository.save(
                User.builder()
                        .id(1L)
                        .email("kok202@naver.com")
                        .nickname("kok202")
                        .address("Seoul")
                        .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                        .status(UserStatus.ACTIVE)
                        .lastLoginAt(0L)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .id(2L)
                        .email("kok303@naver.com")
                        .nickname("kok303")
                        .address("Seoul")
                        .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                        .status(UserStatus.PENDING)
                        .lastLoginAt(0L)
                        .build()
        );
        this.userService = UserService.builder()
                .userRepository(userRepository)
                .certificationService(new CertificationService(mailSender))
                .uuidHolder(new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"))
                .clockHolder(new TestClockHolder(1678530673958L))
                .build();
    }

    @Test
    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        String email = "kok202@naver.com";

        // when
        User result = userService.getByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    void getByEmail은_PENDING_상태인_유저는_찾아올_수_없다() {
        // given
        String email = "kok303@naver.com";

        // when
        // then
        assertThatThrownBy(() -> {
            User result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void getById는_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        // when
        User result = userService.getById(1);

        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    void getById는_PENDING_상태인_유저는_찾아올_수_없다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            User result = userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void userCreate_를_이용하여_유저를_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("kok202@kakao.com")
                .address("Gyeongi")
                .nickname("kok202-k")
                .build();

        // when
        User result = userService.create(userCreate);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(result.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
    }

    @Test
    void userUpdateDto_를_이용하여_유저를_수정할_수_있다() {
        // given
        UserUpdate userUpdate = UserUpdate.builder()
                .address("Incheon")
                .nickname("kok202-n")
                .build();

        // when
        userService.update(1, userUpdate);

        // then
        User user = userService.getById(1);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getAddress()).isEqualTo("Incheon");
        assertThat(user.getNickname()).isEqualTo("kok202-n");
    }

    @Test
    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given
        // when
        userService.login(1);

        // then
        User user = userService.getById(1);
        assertThat(user.getLastLoginAt()).isEqualTo(1678530673958L);
    }

    @Test
    void PENDING_상태의_사용자는_인증_코드로_ACTIVE_시킬_수_있다() {
        // given
        // when
        userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");

        // then
        User user = userService.getById(2);
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void PENDING_상태의_사용자는_잘못된_인증_코드를_받으면_에러를_던진다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaac");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }

}
