package me.study.jpaspecifications.repository;

import java.util.List;
import java.util.Optional;
import me.study.jpaspecifications.model.Member;
import me.study.jpaspecifications.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByTeam(Team team);

    List<Member> findAllByTeam(Team team);
}
