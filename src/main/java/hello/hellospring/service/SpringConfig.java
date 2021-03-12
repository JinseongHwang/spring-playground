package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
