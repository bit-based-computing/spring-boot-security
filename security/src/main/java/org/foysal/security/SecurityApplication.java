package org.foysal.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// basic auth
//@ComponentScan(basePackages = {"basic.auth"})
//@ComponentScan(basePackages = {"custom.filters"})
//@ComponentScan(basePackages = {"custom.authentication.provider"})
//@ComponentScan(basePackages = {"form.login"})
//@ComponentScan(basePackages = {"endpoint.level.authorization"})
//@ComponentScan(basePackages = {"csrf"})
@ComponentScan(basePackages = {"method.level.authorization"})
@ComponentScan(basePackages = {"org.foysal.security"})

public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
