package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
/** Request Scope = HTTP 요청 1개 당 생성되고 끝나면 함께 소멸된다.
 * 적용 대상이 클래스: TARGET_CLASS, 인터페이스: INTERFACES
 * MyLogger의 가짜 프록시 클래스를 만들어 두고 HTTP Request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘 수 있다.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + " [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
