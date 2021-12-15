package me.study.querydsl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import me.study.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        final Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        final Member foundMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(foundMember).isEqualTo(member);

        final List<Member> result1 = memberJpaRepository.findAll();
        assertThat(result1).containsExactly(member);

        final List<Member> result2 = memberJpaRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);
    }
}