package com.example.Log_Api.Config;

import com.example.Log_Api.Handle.CustomAccessDeniedHandler;
import com.example.Log_Api.Handle.CustomFailureHandler;
import com.example.Log_Api.Handle.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private CustomFailureHandler customFailureHandler;
    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler)
                        .failureHandler(customFailureHandler)
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")               // Endpoint xử lý logout
                        .invalidateHttpSession(true)        // Xoá session
                        .clearAuthentication(true)          // Xoá thông tin Authentication
                        .deleteCookies("JSESSIONID")        // Xoá cookie session
                        .logoutSuccessUrl("/login?logout")  // Redirect về login sau khi logout
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }
}
