package com.jskno.spring.resource.server2.config;

import com.jskno.spring.resource.server2.filters.TenantFilter;
import com.jskno.spring.resource.server2.filters.TenantFilterOnce;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /*
    This is taken as it is from the class SpringBootWebSecurityConfiguration
    So it is the Spring default config
    We only add 2 filters
     */
    @Bean
    @Order(2147483642)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        // Custom code here
        http.addFilterAfter(new TenantFilter(), AnonymousAuthenticationFilter.class);
        http.addFilterAfter(new TenantFilterOnce(), AnonymousAuthenticationFilter.class);

        return (SecurityFilterChain)http.build();
    }
}
