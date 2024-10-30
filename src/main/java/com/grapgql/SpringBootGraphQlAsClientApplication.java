package com.grapgql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootGraphQlAsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphQlAsClientApplication.class, args);
        System.err.println("Spring Boot GraphQL Application Started...");
    }

}
