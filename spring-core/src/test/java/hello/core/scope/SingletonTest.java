package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    /**
     * [실행 결과]
     * SingletonBean.init
     * singletonBean1 = hello.core.scope.SingletonTest$SingletonBean@1c5920df
     * singletonBean2 = hello.core.scope.SingletonTest$SingletonBean@1c5920df
     * 15:49:57.405 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@5c909414, started on Tue Apr 27 15:49:57 KST 2021
     * SingletonBean.destroy
     *
     * [싱글톤 스코프 빈 분석]
     * 빈 초기화 메서드를 실행하고,
     * 서로 같은 인스턴스의 빈을 조회한다.
     * 종료 메서드도 정상적으로 호출된다.
     */

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isSameAs(singletonBean2); // 서로 같다.

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
