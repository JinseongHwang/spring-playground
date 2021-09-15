package io.security.basicsecurity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity // 보안 관련 여러 클래스들을 import 해서 실행하는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 상속 필수

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http // 인가 정책
            .authorizeRequests()
            .anyRequest().authenticated();

        http // 인증 정책
            .formLogin()
//            .loginPage("/loginPage") // 사용자 정의 로그인 페이지
            .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
            .failureUrl("/login") // 로그인 실패 후 이동 페이지
            .usernameParameter("userId") // 아이디 파라미터명
            .passwordParameter("passwd") // 패스워드 파라미터명
            .loginProcessingUrl("/login_proc") // 로그인 폼 Action url
            .successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 후 핸들러
                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                    System.out.println("authentication = " + authentication.getName());
                    httpServletResponse.sendRedirect("/");
                }
            })
            .failureHandler(new AuthenticationFailureHandler() { // 로그인 실패 후 핸들러
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                    System.out.println("exception = " + e.getMessage());
                    httpServletResponse.sendRedirect("/login");
                }
            })
            .permitAll();

        http
            .logout() // 로그아웃 과정
            .logoutUrl("/logout") // 로그아웃 처리 URL
            .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
            .deleteCookies("JSESSIONID", "remember-me") // 로그아웃 후 삭제할 쿠키 이름들
            .addLogoutHandler(new LogoutHandler() { // 로그아웃 핸들러
                @Override
                public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                    Authentication authentication) {
                    HttpSession session = httpServletRequest.getSession();
                    session.invalidate(); // 세션 무효화
                }
            })
            .logoutSuccessHandler(new LogoutSuccessHandler() { // 로그아웃 성공 후 핸들러
                @Override
                public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                    // 위에서 .logoutSuccessUrl() 를 작성해줬기 때문에 의미 없음
                    httpServletResponse.sendRedirect("/login"); // 로그인 화면으로 리다이렉트
                }
            });

        http
            .rememberMe() // Remember-Me 기능
            .rememberMeParameter("remember") // 기본 파라미터 명
            .tokenValiditySeconds(3600) // sec, Default = 14days
            .alwaysRemember(false) // 사용자 의견과 상관 없이 Remember me 기능 활성화
            .userDetailsService(userDetailsService);

        http
            .sessionManagement() // 세션 관리 기능 동작
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 정책
            .invalidSessionUrl("/invalid") // 세션이 유효하지 않을 때 이동 할 페이지
            .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
            .expiredUrl("/expired") // 세션이 만료된 경우 이동 할 페이지
            .maxSessionsPreventsLogin(true) // 동시 로그인 차단함, false: 기존 세션 만료 정책(default)
            .and()
            .sessionFixation().changeSessionId(); // 세션 고정 보호 방법

        http.csrf();
    }
}
