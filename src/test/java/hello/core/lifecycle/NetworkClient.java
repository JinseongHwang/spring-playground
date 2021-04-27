package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

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

    /**
     * InitializingBean, DisposableBean 인터페이스를 사용해서 초기화, 소멸했을 때 단점
     * 1. 스프링 전용 인터페이스이다. 따라서 내 프로그램이 스프링에 의존해야 한다.
     * 2. 초기화, 소멸 메서드를 오버라이드하기 때문에 메서드 이름을 변경할 수 없다.
     * 3. 외부 코드를 가져오는 것이기 때문에 내가 코드를 고칠 수 없다.
     * -> 따라서 지금은 거의 사용되지 않는 방법이다.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 의존관계 주입이 끝나면 호출된다.
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        // 빈이 종료될 때 호출된다.
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
