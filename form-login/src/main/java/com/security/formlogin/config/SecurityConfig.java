package com.security.formlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() { // 패스워드 암호화에 사용 할 구현체 지정
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/").authenticated() // 인증이 필요한 주소
                        .anyRequest().permitAll())
                .formLogin(login -> login
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login") // Security 가 대신 로그인 진행
                        .defaultSuccessUrl("/") // 로그인 성공 후 이동할 주소
                        .permitAll())
                .build();
    }
}