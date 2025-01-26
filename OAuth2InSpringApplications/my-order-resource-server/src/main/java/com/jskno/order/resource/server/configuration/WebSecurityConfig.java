package com.jskno.order.resource.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  { 
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 
	   // Make communication STATELESS
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Public API endpoint
		http.authorizeHttpRequests(authorizeRequest ->
			authorizeRequest.requestMatchers("/orders/status/check").permitAll());

		// Configure antMatchers if needed
		http.authorizeHttpRequests(authorizeRequests ->
			authorizeRequests.anyRequest().authenticated());

		http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));
 
		return http.build();
	}
}