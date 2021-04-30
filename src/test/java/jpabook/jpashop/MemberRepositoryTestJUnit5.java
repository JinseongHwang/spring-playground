package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 실제 스프링 프레임워크를 띄워서 테스트를 진행. -> Autowired로 주입 테스트를 하기 위함.
public class MemberRepositoryTestJUnit5 {

    @Autowired private MemberRepository memberRepository;

//    @Autowired
//    public MemberRepositoryTestJUnit5(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Test
    // Spring의 어노테이션을 가져오는 것을 선호
    // Transactional 어노테이션이 테스트코드에 있으면 실행 후 Rollback을 하기 때문에 Commit 안됨
    @Transactional
    @Rollback(value = false) // Rollback을 강제로 무시하는 방법
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberABC");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        System.out.println("findMember = " + findMember);
        System.out.println("member = " + member);
        assertThat(findMember).isEqualTo(member);
    }
}