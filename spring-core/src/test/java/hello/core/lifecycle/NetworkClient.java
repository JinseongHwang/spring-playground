package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    // 인터페이스 방법: +implements InitializingBean, DisposableBean

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + ", message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    /** [Solution 01]
     * InitializingBean, DisposableBean 인터페이스를 사용해서 초기화, 소멸했을 때 단점
     * 1. 스프링 전용 인터페이스이다. 따라서 내 프로그램이 스프링에 의존해야 한다.
     * 2. 초기화, 소멸 메서드를 오버라이드하기 때문에 메서드 이름을 변경할 수 없다.
     * 3. 외부 코드를 가져오는 것이기 때문에 내가 코드를 고칠 수 없다.
     * -> 따라서 지금은 거의 사용되지 않는 방법이다.
     */
    /** [Solution 02]
     * 빈 설정 정보를 활용하면 원하는 메서드 명으로 수정 가능하다.
     */
    /** [Solution 03]
     * @PostConstruct 와 @PreDestroy 어노테이션을 사용하면 모두 깔끔하게 해결된다.
     * 1. javax.annotation 패키지에 포함된 어노테이션이기 때문에 자바 표준에 포함된다.
     * 2. 유일한 단점으로, 외부 라이브러리에 적용하지 못한다. 필요하다면 Solution 02를 사용하자.
     */

    @PostConstruct
    public void init() {
        // 의존관계 주입이 끝나면 호출된다.
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        // 빈이 종료될 때 호출된다.
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
