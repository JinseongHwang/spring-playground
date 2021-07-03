package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    /**
     * 프로덕트 코드에서는 생성자 DI 방식으로 작성하는 것이 좋지만,
     * 테스트 코드에서는 필드 DI 방식으로 작성해도 무방하다.
     * 기존의 MemberServiceTest 코드의 BeforeEach 기능을 대신한다.
     */
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /**
     * AfterEach 기능도 삭제 가능하다. 메모리에서 작동하는 테스트가 아니기 때문이다.
     * 상단에 Transactional 을 테스트 케이스에 달면, Transaction을 먼저 실행하고,
     * INSERT 쿼리 등 테스트를 실행하고, 테스트가 끝나면 Rollback을 실행한다.
     */

    // 자동 변수 생성 : Ctrl + Alt + V
    // static import 생성 : Alt + Enter

    @Test
    // @Commit -> INSERT 쿼리 실행 후 Rollback 대신에 Commit이 되도록 한다. DB에 INSERT가 실제로 발생하게 됨.
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

        // 2번째 인자에 있는 람다함수를 실행했을 때 첫 번째 예외가 발생하면 테스트 통과이다.
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 에러 메시지로 검증하고 싶다면 아래와 같이 작성해도 무방하다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}