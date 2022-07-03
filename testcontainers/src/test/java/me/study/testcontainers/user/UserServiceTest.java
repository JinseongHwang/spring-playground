package me.study.testcontainers.user;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PreDestroy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 생명주기 : 클래스 단위, @BeforeAll 에서 non-static 사용하기 위함
@ActiveProfiles("test")
@Transactional
@SpringBootTest
class UserServiceTest {
    static final String MYSQL_IMAGE = "mysql:8";
    static final MySQLContainer MYSQL_CONTAINER;

    static {
        MYSQL_CONTAINER = new MySQLContainer(MYSQL_IMAGE);
        MYSQL_CONTAINER.start();
    }

    @PreDestroy
    void cleanUp() {
        MYSQL_CONTAINER.stop();
    }

    // 실제 UserService 객체를 사용한다.
    // UserService가 의존하고 있는 UserRepository 역시 실제 객체를 사용한다.
    @Autowired
    private UserService userService;

    @BeforeAll
    void saveUser() {
        // set up dummy data
        final UserEntity eddy1 = userService.saveUser(new UserEntity("eddy1", 21));
        final UserEntity eddy2 = userService.saveUser(new UserEntity("eddy2", 22));
        final UserEntity eddy3 = userService.saveUser(new UserEntity("eddy3", 23));
        final UserEntity eddy4 = userService.saveUser(new UserEntity("eddy4", 24));

        System.out.println(eddy1);
        System.out.println(eddy2);
        System.out.println(eddy3);
        System.out.println(eddy4);
    }

    @Test
    void getUserById() {
        final UserEntity eddy1 = userService.getUserById(1L);
        final UserEntity eddy2 = userService.getUserById(2L);
        final UserEntity eddy3 = userService.getUserById(3L);
        final UserEntity eddy4 = userService.getUserById(4L);

        assertThat(eddy1.getUsername()).isEqualTo("eddy1");
        assertThat(eddy2.getUsername()).isEqualTo("eddy2");
        assertThat(eddy3.getUsername()).isEqualTo("eddy3");
        assertThat(eddy4.getUsername()).isEqualTo("eddy4");
        assertThat(eddy1.getAge()).isEqualTo(21);
        assertThat(eddy2.getAge()).isEqualTo(22);
        assertThat(eddy3.getAge()).isEqualTo(23);
        assertThat(eddy4.getAge()).isEqualTo(24);
    }

    @Test
    void getUserCount() {
        final long userCount = userService.getUserCount();
        assertThat(userCount).isSameAs(4L);
    }
}