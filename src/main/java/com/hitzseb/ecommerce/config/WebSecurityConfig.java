package com.hitzseb.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/register", "/confirmation", "/product", "/product/*", "/health").permitAll()
                        .requestMatchers("/cart", "/payment", "/order").hasAuthority("USER")
                        .requestMatchers("/admin/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").hasAuthority("ADMIN")
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
                .rememberMe(Customizer.withDefaults());;

        return http.build();

    }

}