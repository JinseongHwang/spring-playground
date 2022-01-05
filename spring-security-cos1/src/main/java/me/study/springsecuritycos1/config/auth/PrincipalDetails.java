package me.study.springsecuritycos1.config.auth;


import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import me.study.springsecuritycos1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 시큐리티가 /login 주소 요청을 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료 되면 시큐리티 session을 만들어준다. -> Security ContextHolder
 * 저장될 수 있는 객체는 -> Authentication 객체
 * Authentication 객체 안에 User 정보가 있어야 됨.
 * User 타입도 지정되어 있는데, UserDetails 타입 객체
 *
 * Security Session => Authentication => UserDetails(=PrincipalDetails)
 */
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private User user; // 컴포지션 방식으로 가져옴

    // 해당 유저의 권한을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 해당 계정이 만료되지 않았나요 ? -> ㅇㅇ
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 해당 계정이 잠금되지 않았나요 ? -> ㅇㅇ
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 해당 계정의 비밀번호가 만료되지 않았나요 ? -> ㅇㅇ
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 해당 계정이 활성화되어 있나요? -> ㅇㅇ
    @Override
    public boolean isEnabled() {
        // 너무 오랫동안 로그인하지 않았을 경우에 휴면 계정으로 전환하고자 함
        return true;
    }
}
