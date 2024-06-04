package com.example.TPXProj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main class fot the Spring-Boot application
 *
 * @author Spring-Boot
 */
@SpringBootApplication
@RestController
public class TpxProjApplication {
    /**
     * Initial call to the Spring-Boot application to get it started
     *
     * @param args Arguments for main method
     */
	public static void main(String[] args) {
		SpringApplication.run(TpxProjApplication.class, args);
	}
}