package com.campus02.camtacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.campus02.camtacts")
public class SpringServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}
}
