package me.study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.study.querydsl.entity.Drink;
import me.study.querydsl.entity.Member;
import me.study.querydsl.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TestUtil {

    @PersistenceContext
    EntityManager localEm;

    @Test
    @Commit
    void commitOnly() {
        init(localEm);
    }

    public static JPAQueryFactory init(EntityManager em) {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        Team teamC = new Team("teamC");

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        Drink drink1 = new Drink("coca-cola", 1500);
        Drink drink2 = new Drink("pepsi", 1300);
        Drink drink3 = new Drink("sprite", 1400);

        em.persist(teamA);
        em.persist(teamB);
        em.persist(teamC);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.persist(drink1);
        em.persist(drink2);
        em.persist(drink3);

        return new JPAQueryFactory(em);
    }
}
