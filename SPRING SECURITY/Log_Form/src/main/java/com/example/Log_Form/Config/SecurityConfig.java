package com.example.Log_Form.Config;

import com.example.Log_Form.Handler.CustomAccessDeniedHandler;
import com.example.Log_Form.Handler.CustomFailureHandler;
import com.example.Log_Form.Handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomFailureHandler  customFailureHandler;
    @Autowired
    private CustomSuccessHandler   customSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login")
                        .failureHandler(customFailureHandler)
                        .successHandler(customSuccessHandler)
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }
}
