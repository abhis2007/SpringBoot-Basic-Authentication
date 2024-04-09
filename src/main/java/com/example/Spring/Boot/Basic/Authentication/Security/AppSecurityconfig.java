package com.example.Spring.Boot.Basic.Authentication.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppSecurityconfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("came");
        http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(  "private").authenticated()
                        .anyRequest().permitAll()
                )
                .csrf().disable() // If csrf is enable then you may ending up facing multiple issue in the POST req Enable during prod.
                .httpBasic(withDefaults()).formLogin() ;
        return http.build();
    }


}