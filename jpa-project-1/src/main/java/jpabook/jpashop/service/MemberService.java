package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final을 가지는 필드의 생성자
public class MemberService {
    /**
     * JPA 에서 데이터를 다루는 모든 로직들은 가능하면 트랜잭션 안에서 실행되어야 한다.
     * Transactional 어노테이션을 해주면 public 으로 된 메서드에 모두 적용된다.
     * Transactional(readOnly = true) 를 하게 되면 조회 연산에서 최적화된다.
     * 데이터 수정이 발생해야 하는 save 메서드에는 따로 Transaction 을 붙여줘서 false 옵션을 준다.
     * 쓰기 연산이 많은지, 읽기 연산이 많은지 보고 판단해서 작성한다.
     */

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // readOnly = false
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
