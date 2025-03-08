package com.example.userAuthentication.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf-> csrf.disable());
        httpSecurity.cors(cors->cors.disable());
        httpSecurity.authorizeHttpRequests(req-> {
            req.requestMatchers("/auth/signin").permitAll()
                    .requestMatchers("/auth/signup").permitAll()
                    .requestMatchers("/auth/validateToken").permitAll()
                    .anyRequest().authenticated();
        });
        return httpSecurity.build();

    }

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
