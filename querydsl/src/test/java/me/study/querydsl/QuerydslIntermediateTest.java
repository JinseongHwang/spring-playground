package me.study.querydsl;

import static me.study.querydsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.study.querydsl.dto.MemberDto;
import me.study.querydsl.dto.QMemberDto;
import me.study.querydsl.dto.UserDto;
import me.study.querydsl.entity.Member;
import me.study.querydsl.entity.QMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslIntermediateTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = TestUtil.init(em);
    }

    @Test
    void simpleProjection() throws Exception {
        final List<String> result = queryFactory
            .select(member.username)
            .from(member)
            .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void tupleProjection() throws Exception {
        final List<Tuple> result = queryFactory
            .select(member.username, member.age)
            .from(member)
            .fetch();

        for (Tuple tuple : result) {
            final String username = tuple.get(member.username);
            final Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }

    @Test
    void findDtoByJPQL() throws Exception {
        final List<MemberDto> result = em.createQuery(
                "select new me.study.querydsl.dto.MemberDto(m.username, m.age) from Member m",
                MemberDto.class
            )
            .getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    void setterProjection() throws Exception {
        // 생성된 setter 로 접근해서 값을 삽입한다.
        final List<MemberDto> result = queryFactory
            .select(Projections.bean(MemberDto.class,
                member.username,
                member.age)
            )
            .from(member)
            .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    void fieldProjection() throws Exception {
        // reflection 같은 기술로 필드에 값을 바로 삽입한다.
        final List<MemberDto> result = queryFactory
            .select(Projections.fields(MemberDto.class,
                member.username,
                member.age)
            )
            .from(member)
            .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    void fieldUserProjection() throws Exception {
        // reflection 같은 기술로 필드에 값을 바로 삽입한다.
        // 필드 명이 일치해야 한다.
        final List<UserDto> result = queryFactory
            .select(Projections.fields(UserDto.class,
                member.username.as("name"), // 필드명이 다를 경우 as
                member.age)
            )
            .from(member)
            .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    void fieldUserSubQueryProjection() throws Exception {
        // reflection 같은 기술로 필드에 값을 바로 삽입한다.
        // 필드 명이 일치해야 한다.

        final QMember memberSub = new QMember("memberSub");

        final List<UserDto> result = queryFactory
            .select(Projections.fields(UserDto.class,
                member.username.as("name"), // 필드명이 다를 경우 as

                ExpressionUtils.as(JPAExpressions
                    .select(memberSub.age.max())
                    .from(memberSub), "age")
            ))
            .from(member)
            .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    void constructorProjection() throws Exception {
        // 생성자를 활용한다.
        final List<MemberDto> result = queryFactory
            .select(Projections.constructor(MemberDto.class,
                member.username,
                member.age)
            )
            .from(member)
            .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    void constructorUserProjection() throws Exception {
        // 생성자를 활용한다.
        final List<UserDto> result = queryFactory
            .select(Projections.constructor(UserDto.class,
                member.username,
                member.age)
            )
            .from(member)
            .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    void queryProjection() throws Exception {
        final List<MemberDto> result = queryFactory
            .select(new QMemberDto(member.username, member.age))
            .from(member)
            .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    /**
     * 파라미터 바인딩 대상이 null 이냐 null 이 아니냐에 따라 동적 쿼리를 작성하는 방법 -> BooleanBuilder 를 사용하는 방법
     */
    @Test
    void dynamicQueryBooleanBuilder() throws Exception {
        String usernameParam = "member1";
//        Integer ageParam = 10;
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        final BooleanBuilder builder = new BooleanBuilder();
//        final BooleanBuilder builder = new BooleanBuilder(member.username.eq(usernameCond)); // username 검증
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
            .selectFrom(member)
            .where(builder)
            .fetch();
    }

    /**
     * 파라미터 바인딩 대상이 null 이냐 null 이 아니냐에 따라 동적 쿼리를 작성하는 방법 -> Where Parameter 를 사용하는 방법
     */
    @Test
    void dynamicQueryWhereParam() throws Exception {
        String usernameParam = "member1";
//        Integer ageParam = 10;
        Integer ageParam = null;

        List<Member> result = searchMember2(usernameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
            .selectFrom(member)
//            .where(usernameEq(usernameCond), ageEq(ageCond))
            .where(allEq(usernameCond, ageCond))
            .fetch();
    }

    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }

    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    private BooleanExpression allEq(String usernameCond, Integer ageCond) {
        return usernameEq(usernameCond)
            .and(ageEq(ageCond));
    }

    @Test
    @Commit
    void bulkUpdate() throws Exception {

        /**
         * DB와 영속성 컨텍스트가 동일함
         * 1 -> member1
         * 2 -> member2
         * 3 -> member3
         * 4 -> member4
         */
        final long count = queryFactory
            .update(member)
            .set(member.username, "비회원")
            .where(member.age.lt(28))
            .execute();
        /**
         * bulk update가 실행된다 -> 영속성 컨텍스트 무시하고 바로 DB에 쿼리 날림
         * << PK -> DB -> Persistence Context >>
         * 1 -> 비회원 -> member1
         * 2 -> 비회원 -> member2
         * 3 -> member3 -> member3
         * 4 -> member4 -> member4
         */
        List<Member> result = queryFactory
            .selectFrom(member)
            .fetch();
        for (Member mem : result) {
            System.out.println("mem = " + mem);
        }
        /**
         * DB에 select 쿼리가 날아간다.
         * DB에서 가져온 값을 영속성 컨텍스트 1차 캐시에 집어 넣으려고 하는데...
         * PK 가 겹치는 값이 이미 존재한다. 그러면 영속성 컨텍스트가 우선권을 가진다. DB에서 가져온 데이터는 버린다.
         * Transaction isolation level 2 : Repeatable read
         * << PK -> DB -> Persistence Context >>
         * 1 -> 비회원 -> member1
         * 2 -> 비회원 -> member2
         * 3 -> member3 -> member3
         * 4 -> member4 -> member4
         */
        /**
         * SQL 저장소의 남은 쿼리를 모두 실행해주고,
         * 영속성 컨텍스트를 비워주면 이 문제를 해결할 수 있다.
         */
        em.flush();
        em.clear();

        result = queryFactory
            .selectFrom(member)
            .fetch();
        for (Member mem : result) {
            System.out.println("mem = " + mem);
        }
    }

    @Test
    void bulkAdd() throws Exception {
        final long count = queryFactory
            .update(member)
            .set(member.age, member.age.add(1))
            .execute();

        em.flush();
        em.clear();

        final List<Member> result = queryFactory
            .selectFrom(member)
            .fetch();
        for (Member mem : result) {
            System.out.println("mem = " + mem);
        }
    }

    @Test
    void bulkDelete() throws Exception {
        final long count = queryFactory
            .delete(member)
            .where(member.age.gt(18))
            .execute();

        final List<Member> result = queryFactory
            .selectFrom(member)
            .fetch();
        for (Member mem : result) {
            System.out.println("mem = " + mem);
        }
    }

    @Test
    void sqlFunction() throws Exception {
        final List<String> result = queryFactory
            .select(Expressions.stringTemplate(
                "function('replace', {0}, {1}, {2})",
                member.username, "member", "M"
            ))
            .from(member)
            .fetch();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void sqlFunction2() throws Exception {
        final List<String> result = queryFactory
            .select(member.username)
            .from(member)
//            .where(member.username.eq(Expressions.stringTemplate(
//                "function('lower', {0})",
//                member.username
//            )))
            .where(member.username.eq(member.username.lower()))
            .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}
