package me.study.jpabasic.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import me.study.jpabasic.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class InitController {

    @PersistenceUnit
    EntityManagerFactory emf;

    @GetMapping("/init")
    public String init() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // start
            Member member = new Member();
            member.setName("jinseong");
            em.persist(member);

            // end
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.warn("{}", e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return "init";
    }

}
