package com.co.softworld.credibanco.configuration;

import com.co.softworld.credibanco.repository.ICustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(HttpMethod.POST, "/card/**").hasRole("admin")
                        .antMatchers(HttpMethod.DELETE, "/card/**").hasRole("admin")
                        .antMatchers(HttpMethod.GET, "/card/**").hasAnyRole("admin", "test")

                        .antMatchers(HttpMethod.POST, "/product/**").hasRole("admin")
                        .antMatchers(HttpMethod.DELETE, "/product/**").hasRole("admin")
                        .antMatchers(HttpMethod.GET, "/product/**").hasAnyRole("admin", "test")

                        .antMatchers(HttpMethod.POST, "/transaction/**").hasRole("admin")
                        .antMatchers(HttpMethod.GET, "/transaction/**").hasAnyRole("admin", "test")

                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(ICustomerRepository customerRepository) {

        return username -> customerRepository.findByUsername(username)
                .map(customer ->
                        User.builder()
                                .username(customer.getUsername())
                                .password(customer.getPassword())
                                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(customer.getRoles()))
                                .build()
                ).orElseThrow(() -> new RuntimeException("Not found customer with username: " + username));
    }
}
