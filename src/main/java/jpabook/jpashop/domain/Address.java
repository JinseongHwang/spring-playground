package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    /**
     * 값 타입은 변경 불가능하게 설계해야 한다.
     * Setter 를 제거하고, 생성자로 값을 초기화하면 불변 객체가 생성된다.
     * 기본 생성자는 protected 로 접근을 제한해서 public 보다 안전하게 관리한다.
     * private 으로 하지 못하게 제약을 두는 이유는, JPA 에서 프록시,리플렉션 같은 기술을 사용하기 위함이다.
     */

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
