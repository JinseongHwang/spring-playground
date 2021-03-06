package me.study.querydsl;

import static com.querydsl.jpa.JPAExpressions.select;
import static me.study.querydsl.entity.QMember.member;
import static me.study.querydsl.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import me.study.querydsl.entity.Member;
import me.study.querydsl.entity.QMember;
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
        queryFactory = TestUtil.init(em);
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
                .and(member.age.between(10, 30))) // 10, 30 포함
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
//        final Member fetchOne = queryFactory
//            .selectFrom(member)
//            .fetchOne();
        assertThrows(
            NonUniqueResultException.class,
            () -> queryFactory.selectFrom(member).fetchOne()
        );

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

    /**
     * 회원 정렬 순서 1. 회원 나이 내림차순(DESC) 2. 회원 이름 오름차순(ASC) 단, 2에서 회원 이름이 null 이면 마지막에 포함(nulls last)
     */
    @Test
    void sort() throws Exception {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        final List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.eq(100)) // age = 100
            .orderBy(member.age.desc(), member.username.asc().nullsLast()) // 나이 내림차순 -> 이름 오름차순(null 은 마지막에)
            .fetch();

        final Member member5 = result.get(0);
        final Member member6 = result.get(1);
        final Member memberNull = result.get(2);
        assertEquals("member5", member5.getUsername());
        assertEquals("member6", member6.getUsername());
        assertNull(memberNull.getUsername());
    }

    @Test
    void paging1() throws Exception {
        final List<Member> result = queryFactory
            .selectFrom(member)
            .orderBy(member.username.desc())
            .offset(0)
            .limit(2)
            .fetch();
        assertEquals(2, result.size());
    }

    @Test
    void paging2() throws Exception {
        final QueryResults<Member> queryResults = queryFactory
            .selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetchResults();
        assertEquals(4, queryResults.getTotal());
        assertEquals(2, queryResults.getLimit());
        assertEquals(1, queryResults.getOffset());
        assertEquals(2, queryResults.getResults().size());
    }

    @Test
    void aggregation() throws Exception {
        final List<Tuple> result = queryFactory
            .select(
                member.count(),
                member.age.sum(),
                member.age.avg(),
                member.age.max(),
                member.age.min()
            )
            .from(member)
            .fetch();
        final Tuple tuple = result.get(0);
        assertEquals(4, tuple.get(member.count()));
        assertEquals(100, tuple.get(member.age.sum()));
        assertEquals(25, tuple.get(member.age.avg()));
        assertEquals(40, tuple.get(member.age.max()));
        assertEquals(10, tuple.get(member.age.min()));
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구하라
     */
    @Test
    void group() throws Exception {
        final List<Tuple> result = queryFactory
            .select(team.name, member.age.avg())
            .from(member)
            .join(member.team, team)
            .groupBy(team.name)
            .fetch();
        final Tuple teamA = result.get(0);
        final Tuple teamB = result.get(1);
        assertEquals("teamA", teamA.get(team.name));
        assertEquals(15, teamA.get(member.age.avg()));
        assertEquals("teamB", teamB.get(team.name));
        assertEquals(35, teamB.get(member.age.avg()));
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    void join() throws Exception {
        final List<Member> result = queryFactory
            .selectFrom(member)
            .join(member.team, team)
            .where(team.name.eq("teamA"))
            .fetch();
        assertThat(result)
            .extracting("username")
            .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인 회원의 이름이 팀이름과 같은 회원 조회
     */
    @Test
    void thetaJoin() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        final List<Member> result = queryFactory
            .select(member)
            .from(member, team)
            .where(member.username.eq(team.name))
            .fetch();
        assertThat(result)
            .extracting("username")
            .containsExactly("teamA", "teamB", "teamC");
    }

    /**
     * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회 JPQL: select m, t from Member m left join m.team t on t.name='teamA';
     */
    @Test
    void joinOnFiltering() throws Exception {
        final List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(member.team, team)
            .on(team.name.eq("teamA"))
            .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    void joinOnFilteringWithSingleJoinArg() throws Exception {
        final List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(team)
            .on(team.name.eq("teamA")
                .and(team.eq(member.team)))
            .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 연관 관계가 없는 엔티티 외부 조인 회원의 이름이 팀이름과 같은 대상 외부 조인
     */
    @Test
    void joinOnNoRelation() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        final List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(team).on(member.username.eq(team.name))
            .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    void fetchJoinNo() {
        em.flush();
        em.clear();

        final Member findMember = queryFactory
            .selectFrom(member)
            .where(member.username.eq("member1"))
            .fetchOne();

        assertThat(emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam()))
            .isFalse();
    }

    @Test
    void fetchJoinYes() {
        em.flush();
        em.clear();

        final Member findMember = queryFactory
            .selectFrom(member)
            .join(member.team, team).fetchJoin()
            .where(member.username.eq("member1"))
            .fetchOne();

        assertThat(emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam()))
            .isTrue();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    void subQuery() throws Exception {
        final QMember memberSub = new QMember("memberSub");

        final List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.eq(
                select(memberSub.age.max())
                    .from(memberSub)
            ))
            .fetch();

        assertThat(result).extracting("age")
            .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원 조회
     */
    @Test
    void subQueryGoe() throws Exception {
        final QMember memberSub = new QMember("memberSub");

        final List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.goe(
                select(memberSub.age.avg())
                    .from(memberSub)
            ))
            .fetch();

        assertThat(result).extracting("age")
            .containsExactly(30, 40);
    }

    /**
     * IN 연습
     */
    @Test
    void subQueryIn() throws Exception {
        final QMember memberSub = new QMember("memberSub");

        final List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.in(
                select(memberSub.age)
                    .from(memberSub)
                    .where(memberSub.age.gt(10))
            ))
            .fetch();

        assertThat(result).extracting("age")
            .containsExactly(20, 30, 40);
    }

    /**
     * IN 연습
     */
    @Test
    void selectSubQuery() throws Exception {
        final QMember memberSub = new QMember("memberSub");

        final List<Tuple> result = queryFactory
            .select(
                member.username,
                select(memberSub.age.avg())
                    .from(memberSub)
            )
            .from(member)
            .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * simple case
     */
    @Test
    void simpleCase() throws Exception {
        final List<String> result = queryFactory
            .select(member.age
                .when(10).then("열살")
                .when(20).then("스무살")
                .otherwise("기타")
            )
            .from(member)
            .fetch();

        for (String res : result) {
            System.out.println("res = " + res);
        }
    }

    /**
     * use CaseBuilder for complex case
     */
    @Test
    void caseBuilder() throws Exception {
        final List<String> result = queryFactory
            .select(new CaseBuilder()
                .when(member.age.between(0, 20)).then("0~20살")
                .when(member.age.between(21, 30)).then("21~30살")
                .otherwise("기타")
            )
            .from(member)
            .fetch();

        for (String res : result) {
            System.out.println("res = " + res);
        }
    }

    /**
     * 상수 표현
     */
    @Test
    void constant() throws Exception {
        final List<Tuple> result = queryFactory
            .select(member.username, Expressions.constant("A"))
            .from(member)
            .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 문자 더하기
     */
    @Test
    void concat() throws Exception {
        final List<String> result = queryFactory
            .select(member.username.concat("_").concat(member.age.stringValue())) // 타입 캐스팅
            .from(member)
            .where(member.username.eq("member1"))
            .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}