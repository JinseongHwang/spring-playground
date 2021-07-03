package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository를 상속받는 interface를 생성해두면,
 * 스프링 데이터 JPA가 interface에 대한 구현체를 알아서 만들어서 스프링 빈에 등록을 한다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
