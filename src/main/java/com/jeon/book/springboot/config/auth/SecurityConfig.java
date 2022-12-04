package com.jeon.book.springboot.config.auth;

import com.jeon.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                // h2-console 화 면을 사용하기 위한 옵션 disable
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리 설정 시작
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 설정 진입
                    .logout()
                        // 로그아웃 성공시 이동 경로
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능에 대한 설정 진입
                    .oauth2Login()
                        // 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 담당할 UserService 인터페이스의 구현체를 등록
                            .userService(customOAuth2UserService);
    }
}
