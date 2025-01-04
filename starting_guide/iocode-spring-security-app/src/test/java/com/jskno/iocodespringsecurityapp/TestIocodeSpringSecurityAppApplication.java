package com.jskno.iocodespringsecurityapp;

import com.jskno.iocode.securyty.app.IocodeSpringSecurityAppApplication;
import org.springframework.boot.SpringApplication;

public class TestIocodeSpringSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(IocodeSpringSecurityAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
