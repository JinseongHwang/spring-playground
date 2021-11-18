package me.study.querydsl;

import static me.study.querydsl.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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

    @Test
    void search() throws Exception {
        final Member findMember = queryFactory
            .selectFrom(member) // select + from
            .where(member.username.eq("member1")
                .and(member.age.between(10, 30)))
            .fetchOne();
        assertEquals("member1", findMember.getUsername());
    }

    @Test
    void searchAndParam() throws Exception {
        final Member findMember = queryFactory
            .selectFrom(member) // select + from
            .where( // and 는 comma로 묶을 수 있다.
                member.username.eq("member1"),
                member.age.eq(10)
            )
            .fetchOne();
        assertEquals("member1", findMember.getUsername());
    }

    @Test
    void resultFetch() throws Exception {
        /**
         * fetch : 리스트 반환, 데이터 없으면 빈 리스트 반환
         */
        final List<Member> fetch = queryFactory
            .selectFrom(member)
            .fetch();

        /**
         * fetchOne: 단건 조회
         * 결과 없으면 null
         * 결과가 둘 이상이면 throw NonUniqueResultException
         */
        final Member fetchOne = queryFactory
            .selectFrom(member)
            .fetchOne();

        /**
         * fetchFirst == limit(1).fetchOne();
         */
        final Member fetchFirst = queryFactory
            .selectFrom(member)
            .fetchFirst();

        /**
         * fetchResults 는 조회 쿼리가 2번 실행된다.
         * 1. 전체 데이터 count 쿼리
         * 2. Member 조회 쿼리
         */
        final QueryResults<Member> fetchResults = queryFactory
            .selectFrom(member)
            .fetchResults();
        final long total = fetchResults.getTotal();
        final List<Member> results = fetchResults.getResults();
        assertEquals(4, total);
        assertEquals(4, results.size());
    }
}
