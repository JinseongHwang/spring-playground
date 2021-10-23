package me.study.javatest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@ExtendWith(FindSlowTestExtension.class) // 기본 1초
public class RealSlowTest {

    /**
     * 다른 시간 기준을 가진 Extension이 필요하다면 생성자를 만들고 아래와 같이 사용.
     */
    @RegisterExtension
    static FindSlowTestExtension extension = new FindSlowTestExtension(2000L);

//    @SlowTest
    @DisplayName("조금 느린 테스트")
    void littleSlow() throws InterruptedException {
        Thread.sleep(500L);
        System.out.println("이 테스트 조금 느리네..?");
    }

//    @SlowTest
    @DisplayName("완전 느린 테스트")
    void realSlow() throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println("이 테스트는 너무 느린 것 같아");
    }
}
