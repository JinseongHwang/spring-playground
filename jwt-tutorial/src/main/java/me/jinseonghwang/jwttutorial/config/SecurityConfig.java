package me.jinseonghwang.jwttutorial.config;

import me.jinseonghwang.jwttutorial.jwt.JwtAccessDeniedHandler;
import me.jinseonghwang.jwttutorial.jwt.JwtAuthenticationEntryPoint;
import me.jinseonghwang.jwttutorial.jwt.JwtSecurityConfig;
import me.jinseonghwang.jwttutorial.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // 웹 보안 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메서드 단위로 사용하기 위해 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 생성자 의존성 주입
    public SecurityConfig(
        TokenProvider tokenProvider,
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
        JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
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
            .csrf().disable() // token 을 사용하기 때문에 csrf 설정을 disable 한다.

            .exceptionHandling() // 예외 설정을 내가 만든 설정으로 setting
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            .and() // h2-console 설정
            .headers()
            .frameOptions()
            .sameOrigin()

            .and() // 세션을 사용하지 않는다.
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests() // HttpServletRequest 를 사용하는 요청에 대한 접근 제한을 설정함
            .antMatchers("/api/hello").permitAll() // 입력한 API 에 대한 접근은 인증 없이 허용
            .antMatchers("/api/authenticate").permitAll() // 토큰이 없는 상태에서 요청하기 때문에 permitAll
            .antMatchers("/api/signup").permitAll() // 위와 마찬가지
            .anyRequest().authenticated() // 나머지 API 에 대한 접근은 인증 요구

            .and()
            .apply(new JwtSecurityConfig(tokenProvider)); // JwtSecurityConfig 설정 추가
    }
}