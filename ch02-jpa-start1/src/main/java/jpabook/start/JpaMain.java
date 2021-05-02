package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        // META-INF/persistence.xml 파일에서 이름이 "jpabook"인 영속성 유닛을 찾아서 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        // 등록: 생성하려는 entity를 persist 메서드의 매개변수로 넘겨 주면 된다.
        em.persist(member);

        // 수정: EntityManager 가 entity의 수정을 추적하고 있기 때문에 아래와 같이 작성해도 된다.
        member.setAge(20);

        // 한 건 조회: find 메서드의 매개변수(조회할 엔티티 타입, 테이블 기본 키)
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        // 목록 조회: 검색쿼리를 수행할 때는 JPQL을 사용해야 한다.
        // createQuery의 매개변수(JPQL 쿼리, 반환 타입)
        // 아래 JPQL 쿼리에서 "Member"는 테이블이 아니라 엔티티이다.
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> members = query.getResultList();
        System.out.println("members.size=" + members.size());

        // 삭제: 삭제하려는 entity를 remove 메서드의 매개변수로 넘겨 주면 된다.
        em.remove(member);
    }
}
