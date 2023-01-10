package com.example.commerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfiguration {

//    private final UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    UserAuthenticationSuccessHandler getSuccessHandler() {
        return new UserAuthenticationSuccessHandler();
    }

    //authenticationManager에 이미 userDetailsService 및 passwordEncoder 생성
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/favicon.ico", "/files/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http
                .authorizeRequests()    // 요청에 의한 보안검사 시작
                .antMatchers(
                        "/",
                        "/member/register",
                        "/member/email_auth",
                        "/member/find/password",
                        "/member/reset/password"
                ).permitAll()
//                .and()
//                .authorizeRequests()    // 요청에 의한 보안검사 시작
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()   // 어떤 요청에도 보안검사를 한다.

                .and()
                .formLogin()   // 보안 검증은 formLogin 방식으로 하겠다.
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler())
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)

                //예외 처리를 할 때, 이동하는 경로
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/denied");


        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(memberService)
//            .passwordEncoder(getPasswordEncoder());
//
//        return (SecurityFilterChain) auth;
//    }

}
