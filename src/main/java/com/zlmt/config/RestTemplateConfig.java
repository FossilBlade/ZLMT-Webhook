package com.zlmt.config;





import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${taxi-api.username:Database}" )
	private String username;
	
	@Value("${taxi-api.password:206972f40164077e45bd014e0e7fadd2}" )
	private String password;
	
	@Value("${taxi-api.url:https://api-zlmt-aa.ligataxi.com/rpc}" )
	private String url;
	

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	RestTemplate restTemplate= builder.rootUri(url).basicAuthentication(username, password).build();
       
       return restTemplate;
    }
    
    
    
    
}