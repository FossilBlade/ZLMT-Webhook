package com.zlmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlmt.model.Client;
import com.zlmt.model.Driver;
import com.zlmt.model.Order;
import com.zlmt.repo.ClientRepository;
import com.zlmt.repo.DriverRepository;
import com.zlmt.repo.OrderRepository;

@RestController
@RequestMapping("/handler")
public class HandlerController {
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

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
	public String home() throws Exception {

		return "<html><body><h1>ZLMT Webhook Handler is running.</h1><p>Use the handler endpoints to save the data.</p></body><html>";

	}

	@RequestMapping(value = "/driver", method = RequestMethod.POST, consumes = "text/plain")
	public Driver processDriver(@RequestBody String payload) throws Exception {
		LOG.info("Payload recieved for Driver: " + payload);

		if (payload.toLowerCase().startsWith("driver") && isPayLoadFormatValid(payload)) {

			String id = payload.split("=")[1];
			String jsonString = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"driver.profile\",\r\n"
					+ "  \"params\": {\r\n" + "    \"driver_id\": %s\r\n" + "    \r\n" + "    \r\n" + "  }\r\n" + "}";

			LOG.debug("Fetching driver with ID '%s' from third party API: " + id);
			JsonNode param_data = getApiResponseParam(id, jsonString);

			if (param_data.size() == 0) {
				LOG.error("No Driver Found by ID: " + id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Driver Found by ID: " + id);

			}

			Driver driver = mapper.treeToValue(param_data, Driver.class);

			return driverRepository.save(driver);

		} else {
			LOG.error("Request not in format 'driverId=456'");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request not in format 'driverId=456'");

		}

	}

	@RequestMapping(value = "/client", method = RequestMethod.POST, consumes = "text/plain")
	public Client processClinet(@RequestBody String payload) throws Exception {
		LOG.info("Payload recieved for Client: " + payload);
		if (payload.toLowerCase().startsWith("phone") && isPayLoadFormatValid(payload)) {

			String phone = payload.split("=")[1];
			String jsonString = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"client.list\",\r\n"
					+ "  \"params\": {\r\n" + "    \r\n" + "    \"filters\": {\r\n"
					+ "      \"phone_number\": \"%s\"\r\n" + "    }\r\n" + "    \r\n" + "  }\r\n" + "}\r\n" + "";

			LOG.debug("Fetching client with phone number '%s' from third party API: " + phone);
			JsonNode param_data = getApiResponseParam(phone, jsonString);

			JsonNode clients = param_data.get("clients");

			if (clients.size() == 0) {
				LOG.error("No Cilent Found by Phone: " + phone);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Cilent Found by Phone: " + phone);

			} else {
				Client client = null;
				for (JsonNode api_client : clients) {
					client = mapper.treeToValue(api_client, Client.class);

					client = clientRepository.save(client);
				}
				return client;
			}

		} else {
			LOG.error("Request not in format 'phone number=25191040000'");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Request not in format 'phone number=25191040000'");

		}

	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "text/plain")
	public Order orderProcess(@RequestBody String payload) throws Exception {
		LOG.info("Payload recieved for Order: " + payload);
		if (payload.toLowerCase().startsWith("order") && isPayLoadFormatValid(payload)) {

			String id = payload.split("=")[1];
			String jsonString = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"order.list\",\r\n"
					+ "  \"params\": {\r\n" + "    \r\n" + "    \"filters\": {\r\n" + "      \"id\": %s\r\n"
					+ "    }\r\n" + "    \r\n" + "  }\r\n" + "}\r\n" + "";

			LOG.debug("Fetching order with ID '%s' from third party API: " + id);
			JsonNode param_data = getApiResponseParam(id, jsonString);

			if (param_data.size() == 0) {
				LOG.error("No Order Found by ID: " + id);
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Order Found by ID: " + id);

			}

			if (param_data.isArray()) {

				JsonNode param_order = param_data.get(0);

				Order order = mapper.treeToValue(param_order, Order.class);

				return orderRepository.save(order);
			} else {
				LOG.error("Returned Param is not an array");
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Returned Param is not an array");
			}

		} else {
			LOG.error("Request not is format 'orderId=456'");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request not is format 'orderId=456'");

		}

	}

	private JsonNode getApiResponseParam(String id, String requestTemplate)
			throws JsonMappingException, JsonProcessingException {
		String requestString = String.format(requestTemplate, id);
		LOG.debug("Request data being sent: " + requestString);

		JsonNode requestObj = mapper.readTree(requestString);

		ResponseEntity<String> result = restTemplate.postForEntity("/", requestObj, String.class);

		String resString = result.getBody();
		LOG.debug("Response recieved: " + resString);

		JsonNode res = mapper.readTree(resString);

		return res.get("params");

	}

	private boolean isPayLoadFormatValid(String payload) {
		return payload.toLowerCase().contains("=") && payload.toLowerCase().split("=").length == 2
				&& payload.toLowerCase().split("=")[1].matches("(0|[1-9]\\d*)");
	}

	@RequestMapping(value = "/syncall", method = RequestMethod.POST, consumes = "text/plain")
	public void processAll() throws Exception {
		LOG.info("Saving/Updating all the previous data.");
		saveAllDrivers();
		saveAllClients();
		saveAllOrders();
		LOG.info("All previous data saved/updated successfully.");

	}

	private void saveAllDrivers() throws Exception {

		String req = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"driver.session.list\"\r\n" + "}";

		JsonNode param_data = getApiResponseParam("dummy", req);

		for (JsonNode api_driver_session : param_data) {
			Long driver_id = api_driver_session.get("driver_id").asLong();
			processDriver("driverId=" + driver_id.toString());

		}

	}

	private void saveAllClients() throws Exception {

		String req = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"client.list\"\r\n" + "}";

		JsonNode param_data = getApiResponseParam("dummy", req);

		JsonNode clients = param_data.get("clients");
		for (JsonNode api_client : clients) {
			Client client = mapper.treeToValue(api_client, Client.class);
			clientRepository.save(client);
		}
	}

	private void saveAllOrders() throws Exception {

		String req = "{\r\n" + "  \"jsonrpc\": \"2.0\",\r\n" + "  \"method\": \"order.list\"\r\n" + "}";

		JsonNode param_data = getApiResponseParam("dummy", req);

		for (JsonNode api_order : param_data) {
			Order order = mapper.treeToValue(api_order, Order.class);
			orderRepository.save(order);
		}
	}

}
