package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }

    /**
     * 테스트 코드 작성 양식
     * 1. Given : 값이 주어지고
     * 2. When : 실행됐을 때
     * 3. Then : 어떠한 결과를 도출해야 한다.
     */
    // 자동 변수 생성 : Ctrl + Alt + V
    // static import 생성 : Alt + Enter

    @Test
    void 회원가입() { // 테스트 함수의 이름은 한글로 작성해도 무방하다.
        // given
        Member member = new Member();
        member.setName("Hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // when
        // then
        memberService.join(member1);

        // 방법 2 (better)
        // 2번째 인자에 있는 람다함수를 실행했을 때 첫 번째 예외가 발생하면 테스트 통과이다.
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 에러 메시지로 검증하고 싶다면 아래와 같이 작성해도 무방하다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        // 방법 1
        // member2를 join했을 때 동일한 에러 메시지가 나오면 성공이다.
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}