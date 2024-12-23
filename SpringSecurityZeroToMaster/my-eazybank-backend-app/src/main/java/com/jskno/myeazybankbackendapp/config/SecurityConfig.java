package com.jskno.myeazybankbackendapp.config;

import com.jskno.myeazybankbackendapp.filter.AuthoritiesLoggingAfterFilter;
import com.jskno.myeazybankbackendapp.filter.AuthoritiesLoggingAtFilter;
import com.jskno.myeazybankbackendapp.filter.JWTTokenGeneratorFilter;
import com.jskno.myeazybankbackendapp.filter.JWTTokenValidatorFilter;
import com.jskno.myeazybankbackendapp.filter.RequestValidationBeforeFilter;
import java.util.Arrays;
import java.util.Collections;
import org.apache.tomcat.websocket.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Please don't generate any JSESSIONID, I will take care on my session management.
        // If I don´t do that SpringSecurity generates by default the JSESSIONID that it stores
        // in the web app and by default it is resent in any subsequent request because
        // that the default browser (GoogleChrome, Firefox,...) behaviour.
        // What I do on my own in the front end is to add the XRSF token to avoid CSRF attacks
        // But the logic of incluid the sessionID on each request it is performed in the browser
        // by default. So it´s a built-in mechanism that belongs to the communication protocol
        // between browser and back (HTTP protocol).
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors().configurationSource((request -> getCorsConfig()));

        // Allow public API, anyone can send contact page and register in the web
        // However other private API like a POST request that activate a loan for my account
        // should have a CSRF mechanism to prevent it's not sent by our own front end app.
        http.csrf().ignoringAntMatchers("/register")
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http
            .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
            // Generation JWT process trigger only once the basic authentication has been successful
            .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
            // Validation JWT process trigger always before the basic authentication has been reached
            .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class);

        http.authorizeRequests()
            .antMatchers("/my-account").hasAuthority("VIEWACCOUNT")
            .antMatchers("/my-balance").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
//            .antMatchers("my-loans").hasAuthority("VIEWLOANS")
            .antMatchers("my-loans").authenticated()
            .antMatchers("/my-cards").hasAuthority("VIEWCARDS")
            .antMatchers("/user", "/contact", "/contacts").authenticated()
            .antMatchers("/register", "/notices").permitAll()
            .and().httpBasic()
            .and().formLogin();

        return http.build();
    }

    private CorsConfiguration getCorsConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        // Exposing the response header so that the browser can access the JWT inserted there.
        config.setExposedHeaders(Arrays.asList(Constants.AUTHORIZATION_HEADER_NAME));
        config.setMaxAge(3600L);
        return config;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
