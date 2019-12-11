package com.zlmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.zlmt.CommandLineAppStartupRunner;

@Service
public class APIService {
	private static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

	@Autowired
	RestTemplate restTemplate;

	@Retryable(maxAttempts = 5, value = ResourceAccessException.class, backoff = @Backoff(delay = 1000, multiplier = 3))
	public ResponseEntity<String> postToApi(JsonNode requestString) {

		try {
			ResponseEntity<String> result = restTemplate.postForEntity("/", requestString, String.class);
			return result;
		} catch (ResourceAccessException ex) {
			LOG.error("Error communicating to the API. Will be retired. Reason: " + ex.getMessage());
			throw ex;
		}

	}

}
