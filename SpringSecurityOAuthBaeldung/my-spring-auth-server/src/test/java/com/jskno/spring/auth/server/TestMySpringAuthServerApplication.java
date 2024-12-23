package com.jskno.spring.auth.server;

import org.springframework.boot.SpringApplication;

public class TestMySpringAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.from(MySpringAuthServerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
