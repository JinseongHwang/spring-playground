package hello.hellospring.config;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * 자동으로 스프링 컨테이너에 올려주는 @Service와 @Repository 어노테이션을 대신해서,
 * 직접 Bean을 작성해서 작동시키는 과정이다.
 *
 * 1. 기존의 MemberService.java 파일의 @Service와  생성자의 @Autowired 어노테이션을 지운다.
 * 2. 기존의 MemoryMemberRepository.java 파일의 @Repository와 생성자의 @Autowired 어노테이션을 지운다.
 * 3. service 패캐지 내에 SpringConfig.java 파일을 생성해서 아래와 같이 작성해준다.
 *
 * 즉, 자동으로 스프링 컨테이너에 올리는 과정을 원한다면 1, 2번 과정을 다시 복구해주면 된다.
 */

@Configuration
public class SpringConfig {

    /*
    JPA를 사용하면 필요없어지는 코드들

    private DataSource dataSource;

    // DataSource는 DB Connection을 획득할 때 사용하는 객체이다.
    // Spring이 resources/application.properties를 보고 DataSource를 자동으로 생성하고 Spring Bean으로 만든 후 DI를 받게 한다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */

    /*
    스프링 데이터 JPA를 사용하면 필요없어지는 코드들

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
     */

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 1개일 경우에는 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /*
    TimeTraceAop의 Component Scan을 대신하는 방법

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
     */

    /*
    스프링 데이터 JPA를 사용하면 필요없어지는 코드들

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
    }
     */
}
