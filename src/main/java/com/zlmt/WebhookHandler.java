package com.zlmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class WebhookHandler {

	public static void main(String[] args) {
		SpringApplication.run(WebhookHandler.class, args);
	}

}
