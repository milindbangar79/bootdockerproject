package com.io.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerBoot {

	@GetMapping("/")
    public String home() {
        return "Hello Docker World";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DockerBoot.class, args);

	}

}
