package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    /**
     * [실행결과]
     * find prototypeBean1
     * prototypeBean.init
     * find prototypeBean2
     * prototypeBean.init
     * prototypeBean1 = hello.core.scope.PrototypeTest$prototypeBean@4b013c76
     * prototypeBean2 = hello.core.scope.PrototypeTest$prototypeBean@53fb3dab
     * 15:51:32.965 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext -
     * Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@3cc2931c, started on Tue Apr 27 15:51:32 KST 2021
     *
     * [프로토타입 스코드 빈 분석]
     * - 싱글톤 스코프 빈은 스프링 컨테이너 생성 시점에 초기화(init) 메서드가 실행되지만,
     *  프로토타입 스코프 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행된다.
     * - 프로토타입 스코프 빈을 2번 조회(생성)했으므로 서로 다른 2개의 빈이 생성된다. 초기화도 2번 실행된다.
     * - 스프링 컨테이너가 "생성 -> 의존관계 주입 -> 초기화" 까지만 관여한다. 컨테이너 종료와는 상관 없기 때문에 @PreDestroy는 호출되지 않는다.
     * - 호출 후 반환 받은 클라이언트에서 빈을 책임지고 관리한다. 반환 이후에는 스프링 컨테이너와 상관없다.
     */

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 서로 다르다.

        ac.close();

        // 수동으로 destroy 가능하다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }

}
