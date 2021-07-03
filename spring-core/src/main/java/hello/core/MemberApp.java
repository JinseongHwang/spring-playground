package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // DIP를 지키지 않는 방법
//        MemberService memberService = new MemberServiceImpl();

        // DIP를 지키나, 순수 Java 코드로 작성됨
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // DIP를 지키고, Spring 컨테이너를 활용
        /**
         * ApplicationContext가 스프링 빈을 포함한 모든 객체를 관리한다.
         * 구현체로서 AnnotationConfigApplicationContext를 사용하는데, 어노테이션 기반의 Config 객체들을 관리한다는 의미이다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
