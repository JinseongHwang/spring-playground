package me.study.querydsl.repository;

import java.util.List;
import me.study.querydsl.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    // select m from Member m where m.username = :username;
    List<Member> findByUsername(String username);
}
