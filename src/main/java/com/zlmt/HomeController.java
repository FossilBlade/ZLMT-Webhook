package com.zlmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlmt.repo.ClientRepository;
import com.zlmt.repo.DriverRepository;
import com.zlmt.repo.OrderRepository;

@RestController
@RequestMapping("/")
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ClientRepository clientRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String home() throws Exception {

		return "<html><body><h1>ZLMT Webhook Handler is running.</h1><p>Use the handler endpoints to save the data.</p></body><html>";

	}

}
