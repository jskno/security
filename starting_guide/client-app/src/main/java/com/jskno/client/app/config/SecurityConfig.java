package com.jskno.client.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                .requestMatchers("/jwks", "/logged-out").permitAll()
                .requestMatchers(HttpMethod.GET, "/authorized").permitAll()
                .requestMatchers(HttpMethod.GET, "/messages").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                .requestMatchers(HttpMethod.POST, "/messages").hasAuthority("SCOPE_write")
                .anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2Login(login -> login.loginPage("/login/oauth2/code/oidc-client"))
//            .oauth2Login(login -> login.loginPage("/login/authorization/oidc-client"))
//            .oauth2Login(Customizer.withDefaults())
            .oauth2Client(Customizer.withDefaults())
            .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
//
//        http
//                .securityMatcher("/messages/**")
//                .authorizeHttpRequests(authorize ->
//                        authorize.requestMatchers("/messages/**").hasAuthority("SCOPE_read")
//                )
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer.jwt(Customizer.withDefaults())
//                );
//        return http.build();
//    }
}
