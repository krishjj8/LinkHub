package com.linkhub.linkservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LinkServiceApplication {

	public static void main(String[] args) {

        SpringApplication.run(LinkServiceApplication.class, args);
	}

}
