package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // 스프링 컨테이너를 종료(close)하기 위해 ConfigurableApplicationContext 타입 객체로 생성한다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        /** [Solution 02]
         * @Bean(initMethod = "init", destroyMethod = "close")
         * 빈 설정 정보 사용 방법 특징
         * 1. 메서드 이름을 자유롭게 지정 가능하다.
         * 2. 스프링 빈이 스프링에 의존하지 않는다.
         * 3. 코드가 아니라 설정 정보를 사용하기 때문에 외부 라이브러리에도 초기화, 종료 메서드를 적용 가능하다.
         * 4. destroyMethod 속성에는 default로 "close", "shutdown"이 매칭되어 있다. default랑 같다면 생략해도 된다.
         */

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
