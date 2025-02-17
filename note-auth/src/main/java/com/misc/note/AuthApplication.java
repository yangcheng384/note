package com.misc.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.misc.note.auth.feign")
@SpringBootApplication
public class AuthApplication {

    public static void main( String[] args ) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
