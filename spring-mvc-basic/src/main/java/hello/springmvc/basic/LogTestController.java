package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 단지 @Controller 라고 하면 메서드 return 값의 문자열이 View 의 논리 이름으로 인식되지만,
 * - @RestController 를 사용하면 return 값의 문자열 그대로 HTTP Message Body 에 담아서 응답한다.
 */
@RestController
@Slf4j
public class LogTestController {

    /**
     * SLF4J 의 Logger 인터페이스를 가져온다. 매개변수로는 현재 클래스를 준다. getClass() or LogTestController.class
     */
//    Lombok 으로 대체 가능하다.
//    private final Logger log = LoggerFactory.getLogger(getClass());
    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // Logging level
        log.trace("trace log={}", name); // Level 1
        log.debug("debug log={}", name); // Level 2
        log.info(" info log={}", name); // Level 3
        log.warn(" warn log={}", name); // Level 4
        log.error("error log={}", name); // Level 5

        return "ok";
    }
}
