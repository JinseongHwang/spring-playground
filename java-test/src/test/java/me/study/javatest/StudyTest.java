package me.study.javatest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.Duration;
import me.study.javatest.domain.Study;
import me.study.javatest.domain.StudyStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// 메서드명의 underscore를 공백으로 치환해준다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("assert 테스트")
    @Tag("fast")
    void create_new_study() throws Exception {
        // given
        final Study study = new Study(10);

        // when
        System.out.println("create");

        // then
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-20));
        assertEquals("limit은 0보다 커야 합니다.", exception.getMessage());

        // 원래는 assert 문을 순서대로 진행하다가, 중간에 실패하면 테스트 진행을 중단한다.
        // 하지만 assertAll 로 묶여있는 assert 문들은 실패와 상관 없이 모두 진행한다.
        assertAll(
            () -> assertNotNull(study),

            // 매개변수 순서 -> <기댓값>, <출력값>, <message>
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "Study를 처음 만들면 상태값이 DRAFT여야 한다."),

            // message를 표현할 때 문자열 연산이 포함된다면, lambda식으로 표현해서 최대한 필요한 경우에만 수행할 수 있다.
            () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );

        // 실행되는 메서드가 종료될 때까지 기다린다. 테스트에 이미 실패했더라도
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(123);
            Thread.sleep(50);
//            Thread.sleep(300); // 100ms 제한인데 300ms를 sleep하면 테스트에 실패한다.
        });
        // 테스트에 실패했다는 것을 알게된 순간 즉시 종료한다. 별도의 쓰레드를 사용하기 때문에 예상치 못한 문제가 생길 수도 있다.
//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new Study(321);
//            Thread.sleep(123_456_789);
//        });
    }

    @Test
    @DisplayName("assume 테스트")
    @Tag("slow")
    void create1() throws Exception {
        // given
        String tempCondition = "CC";
        // 해당 조건이 참일 때만 다음 코드라인으로 진행한다.
        // 만족하지 못하면 Ignore 된다.
        assumeTrue("AA".equalsIgnoreCase(tempCondition));

        // 해당 조건이 참일때만 뒤에 Executable을 실행한다. assumeTrue 와 달리, 거짓이어도 테스트를 중단하지 않는다.
        assumingThat("AA".equalsIgnoreCase(tempCondition) || "BB".equalsIgnoreCase(tempCondition), () -> {
            System.out.println("tempCondition은 BB입니다.");
        });
        assumingThat("AA".equalsIgnoreCase(tempCondition) || "BB".equalsIgnoreCase(tempCondition) || "CC".equalsIgnoreCase(tempCondition), () -> {
            System.out.println("tempCondition은 CC입니다.");
        });

        System.out.println("Study 만들기 성공 ~!~");
        // when
        // then
    }

    @Disabled // 테스트하지 않음
    @Test
    @DisplayName("이건 무시되는게 맞음")
    void createDisable() throws Exception {
        System.out.println("disabled");
    }

    @BeforeAll // 모든 메서드의 시작 시 1번 수행됨
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll // 모든 메서드의 종료 시 1번 수행됨
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach // 각 메서드가 시작하기 직전에 수행됨
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach // 각 메서드가 종료된 직후에 수행됨
    void afterEach() {
        System.out.println("afterEach");
    }
}