package com.yj510.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
   // private final PrincipalDetailsService principalDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (개발 단계)
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // iframe 허용
                        .contentSecurityPolicy(csp -> csp.policyDirectives("img-src * data:;")) // 외부 이미지 허용
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/itemList", "/signup", "/login", "/login-process","/styles/**", "/javascript/**", "/images/**").permitAll() // 정적 리소스 허용
                        .requestMatchers("/itemEdit/**", "/deleteItem/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("email").passwordParameter("password")
                        .defaultSuccessUrl("/itemList", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
