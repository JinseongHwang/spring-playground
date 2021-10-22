package me.study.javatest.member;

import java.util.Optional;
import me.study.javatest.domain.Member;
import me.study.javatest.domain.Study;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    Optional<Member> findByEmail(String email);

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
