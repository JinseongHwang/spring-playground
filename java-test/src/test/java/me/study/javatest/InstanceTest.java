package me.study.javatest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * 기본 테스트 인스턴스 생성 전략은, 메서드 1개당 클래스 인스턴스를 1개씩 따로 생성한다.
 * 하지만 아래와 같이 클래스 인스턴스 생명주기를 클래스 단위로 설정해주면, 클래스 인스턴스가 1개만 생성되고 테스트가 진행된다.
 * 성능 상 이점이 있겠으나, 공유 변수에 대해 사용을 주의해야 한다.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class InstanceTest {

    int count = 0;

    /**
     * 클래스 인스턴스 생명주기가 클래스 단위가 되면, BeforeAll, AfterAll이 static 메서드일 필요가 없다.
     */
    @BeforeAll
    void beforeAll() {
        System.out.println("beforeAll");
    }

    @Test
    void instance1() throws Exception {
        // given
        System.out.println(count++);
        System.out.println(this); // 동일한 인스턴스이다.
        // when
        // then
    }

    @Test
    void instance2() throws Exception {
        // given
        System.out.println(count++);
        System.out.println(this); // 동일한 인스턴스이다.
        // when
        // then
    }

}
