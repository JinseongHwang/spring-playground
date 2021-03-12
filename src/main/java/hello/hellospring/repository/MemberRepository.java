package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // findBy~ 의 경우에는 null이 반환될 수도 있다.
    // null을 반환하는 것보다 Optional 객체에 감싸서 반환하는 것을 선호한다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
