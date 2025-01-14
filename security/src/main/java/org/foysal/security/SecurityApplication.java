package org.foysal.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// basic auth
@ComponentScan(basePackages = {"basic.auth"})
@ComponentScan(basePackages = {"org.foysal.security"})

public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
