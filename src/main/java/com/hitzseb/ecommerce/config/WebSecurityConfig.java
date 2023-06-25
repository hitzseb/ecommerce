package com.hitzseb.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf((csrf) -> csrf.disable())
//                .cors(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/register", "/confirmation",
                                "/product", "/product/*", "/product/image/**", "/health").permitAll()
                        .requestMatchers("/cart", "/payment", "/order").hasAuthority("USER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .deleteCookies("remove")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/logout-success")
                        .permitAll()
                )
                .rememberMe(Customizer.withDefaults());
        ;

        return http.build();

    }

}