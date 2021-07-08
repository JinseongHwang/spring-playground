package me.jinseonghwang.jwttutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 웹 보안 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring() // 아래 요청들은 security 적용에서 무시한다.
            .antMatchers(
                "/h2-console/**", // h2 DB 관련 접근
                "/favicon.ico" // favicon 접근
            );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() // HttpServletRequest 를 사용하는 요청에 대한 접근 제한을 설정함
            .antMatchers("/api/hello").permitAll() // 입력한 API 에 대한 접근은 인증 없이 허용
            .anyRequest().authenticated(); // 나머지 API 에 대한 접근은 인증 요구
    }
}
