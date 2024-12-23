package com.jskno.myeazybankbackendapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
// debug = true --> Not for PRODUCTION. Remove annotation.
@EnableWebSecurity(debug = true)
public class MyEazybankBackendAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEazybankBackendAppApplication.class, args);
    }

}
