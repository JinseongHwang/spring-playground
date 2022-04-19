package me.study.jpaspecifications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.study.jpaspecifications.model.Member;
import me.study.jpaspecifications.model.Team;
import me.study.jpaspecifications.repository.MemberRepository;
import me.study.jpaspecifications.repository.spec.MemberSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class JPATest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void clearEm() {
        em.clear();
    }

    @Test
    void specification() throws Exception {
        // given
        final Team teamA = new Team("teamA");
        em.persist(teamA);

        final Member member1 = new Member("member1", 0, teamA);
        final Member member2 = new Member("member2", 0, teamA);
        em.persist(member1);
        em.persist(member2);

        em.flush();
        em.clear();

        // when
        final Specification<Member> spec1 = MemberSpec.username("member1");
        final Specification<Member> spec2 = MemberSpec.teamName("teamA");
        final Specification<Member> spec3 = MemberSpec.username("member2").and(MemberSpec.teamName("teamA"));

        final List<Member> result1 = memberRepository.findAll(spec1);
        final List<Member> result2 = memberRepository.findAll(spec2);
        final List<Member> result3 = memberRepository.findAll(spec3);

        // then
        assertEquals(1, result1.size());
        assertEquals(2, result2.size());
        assertEquals(1, result3.size());

        /**
         * JPA Specification 방식을 사용해도 영속성 컨텍스트에 데이터를 적재함.
         */
        assertTrue(em.contains(result1.get(0)));
        assertTrue(em.contains(result2.get(0)));
        assertTrue(em.contains(result2.get(1)));
        assertTrue(em.contains(result3.get(0)));

        /**
         * But, 1차 캐시를 이미 위에서 날렸기 때문에 기존 객체랑은 다르다.
         */
        assertNotEquals(member1, result1.get(0));
        assertNotEquals(member2, result3.get(0));

        /**
         * 새로 조회해서 EM에 넣어준 객체와는 동일하다.
         */
        assertEquals(em.find(Member.class, member1.getId()), result1.get(0));
        assertEquals(em.find(Member.class, member1.getId()), result2.get(0));
        assertEquals(em.find(Member.class, member2.getId()), result2.get(1));
        assertEquals(em.find(Member.class, member2.getId()), result3.get(0));
    }

    @Test
    void jpa() throws Exception {
        // Given
        final Team teamA = new Team("teamA");
        em.persist(teamA);

        final Member member1 = new Member("member1", 0, teamA);
        final Member member2 = new Member("member2", 0, teamA);
        em.persist(member1);
        em.persist(member2);

        em.flush(); // SQL 저장소의 쿼리들을 실제 DB로 날림
        em.clear(); // 영속성 컨텍스트에 저장된 객체들을 모두 삭제함.

        // When
        /**
         * 1. 영속성 컨텍스트에 먼저 조회해보니까 없네..?
         * 2. 실제 DB에 쿼리 날려서 데이터 가져옴
         * 3. 영속성 컨텍스트에 없었으니까 올려놓고 사용자한테 반환
         */
        final Member m1 = memberRepository.findByUsername("member1").get();
        final List<Member> t = memberRepository.findAllByTeam(teamA);

        // Then
        assertTrue(em.contains(m1));

        assertEquals(2, t.size());
        assertTrue(em.contains(t.get(0)));
        assertTrue(em.contains(t.get(1)));
    }

}
