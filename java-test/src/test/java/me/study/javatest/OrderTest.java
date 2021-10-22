package me.study.javatest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class) // Order 어노테이션으로 메서드 실행 순서를 결정할 수 있다.
public class OrderTest {

    /**
     * Spring의 Order가 아니라 JUnit의 Order이다
     * 숫자가 낮을 수록 우선순위를 가진다.
     * 숫자가 동일해도 오류는 발생하지 않지만, 순서롤 결정해주기로 했으면 겹치게 작성하지 않도록 주의하자.
     */
    @Order(1)
    @Test
    void order1() throws Exception {
        // given
        System.out.println("order 111");
        System.out.println(this); // 서로 다른 인스턴스
        // when
        // then
    }

    @Order(2)
    @Test
    void order2() throws Exception {
        // given
        System.out.println("order222");
        System.out.println(this); // 서로 다른 인스턴스
        // when
        // then
    }
}
