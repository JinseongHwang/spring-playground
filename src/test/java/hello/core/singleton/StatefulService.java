package hello.core.singleton;

public class StatefulService {

    /**
     * 이 클래스는 스프링 빈 클래스이다.
     * 스프링 빈은 아래와 같이 상태를 유지(stateful)하면 안된다.
     * 객체가 공유되면 서로 다른 사람이 하나의 객체에 접근할 가능성을 열게 되는 것이다.
     * 즉, 아래 코드들은 잘못된 설계 형태이다.
     *
     * 이를 해결하기 위해서는 무상태(stateless)로 설계해야 한다.
     * - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
     * - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
     * - 가급적 읽기만 가능해야 한다.
     * - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
     *
     * 아래 코드를 수정해보면,
     * 1. price 인스턴스 변수, getPrice() 메서드를 삭제한다.
     * 2. order() 메서드로 들어온 price 매개변수를 바로 return 한다.
     * 3. order() 메서드를 호출할 때, 반환값이 주문한 금액이 되는 것이다.
     */
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}
