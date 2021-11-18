package me.study.querydsl;

import static me.study.querydsl.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.study.querydsl.entity.Member;
import me.study.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamA);

        Member member3 = new Member("member3", 10, teamB);
        Member member4 = new Member("member4", 10, teamB);

        em.persist(teamA);
        em.persist(teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    void startJpql() throws Exception {
        // member1을 찾아라
        final Member findMember = em.createQuery("select m from Member m where m.username=:username", Member.class)
            .setParameter("username", "member1")
            .getSingleResult();
        assertEquals("member1", findMember.getUsername());
    }

    @Test
    void startQuerydsl() throws Exception {
        final Member findMember = queryFactory
            .select(member)
            .from(member)
            .where(member.username.eq("member1"))
            .fetchOne();
        assertEquals("member1", findMember.getUsername());
    }
}
