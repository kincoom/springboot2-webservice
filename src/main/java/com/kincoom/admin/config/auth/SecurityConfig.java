package com.kincoom.admin.config.auth;

import com.kincoom.admin.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()     //h2-concole 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests()                //URL별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMatchers 옵션 사용가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    //권한관리 대상 지정 옵션, URL, HTTP 메소드별로 관리가능, "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한, POST 메소드이면서 "/api/v1/**" 주소를 가진 API는 권한을 가진사람만 가능하도록 설정
                    .anyRequest().authenticated()       // 설정된 값들 이외 나머지 URL, authenticated를 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게함(로그인한 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")          //로그아웃 기능에대한 설정의 진입점, 로그아웃 성공시 /주소로 이동
                .and()
                    .oauth2Login()      //OAuth2 로그인 기능에 대한 설정 진입점
                        .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담담
                            .userService(customOAuth2UserService);      // 소셜 로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록, 리소스서버에서 사용자 정보를 가져온 상태에서 추가롤 진행하고자 하는 기능 명시할 수 있음.
    }
}
