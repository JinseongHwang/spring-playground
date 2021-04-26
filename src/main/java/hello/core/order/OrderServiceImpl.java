package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // 필수값인 final 필드 2개를 매개변수로 받는 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService {

    // 아래와 같이 작성하면 현재 클래스(OrderServiceImpl)가 구현 객체에 의존하게 된다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // AppConfig와 생성자를 통해 의존성을 주입받으면 해결할 수 있다.
    // final 키워드가 있으면 선언과 동시에 할당하거나, 생성자를 통해 할당해줘야 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* Lombok: RequiredArgsConstructor 을 추가하면 100% 동일한 코드가 추가된다.
    하지만 테스트 및 학습 목적으로 아래 코드를 남겨둔다. */
    /** @Autowired에 대해서,
     * 1. 생성자가 단 하나만 존재한다면 Autowired 어노테이션 생략이 가능하다.
     * 2-1. 우선적으로 스프링 컨테이너에서 타입으로 빈을 찾아서 연결한다.
     * 2-2. 일치하는 타입의 빈이 2개 이상 존재한다면 필드명 또는 파라미터 명으로 빈을 찾아서 연결한다.
     * 3-1. 이름을 의도적으로 바꾸는 것이 싫다면 @Qualifier 도 존재한다. 추가적인 구분자를 만들어주는 역할이다.
     * 3-2. @Qualifier 구분자와 매칭되지 않았다면 그 구분자와 동일한 이름의 빈을 찾아서 연결하는 시도를 한다.(추천X)
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        // 생성자를 통해 DI를 하고 필드 객체에 final 키워드를 붙이면 여러 잡다한 오류에 힘쓰지 않아도 된다. -> 안정성 Up
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
