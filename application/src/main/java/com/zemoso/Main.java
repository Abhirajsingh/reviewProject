package com.zemoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main class contains main method. It will use all modules and start the application.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
