# Webhook Handler for ZLMT

It is a springboot based REST API, which exposes the following endpoints, to handler the request coming from webhooks:

- `POST /handler/client`: This takes the client phone number, coming from webhook and fetches the client information based on it from third party API to save to the database. Format of input data is `phoneNumber=2545600000`

- `POST /handler/driver`: This takes the driver ID, coming from webhook and fetches the driver information based on it from third party API to save to the database. Format of input data is `driverId=290`

- `POST /handler/order`: This takes the order ID, coming from webhook and fetches the client information based on it from third party API to save to the database. Format of input data is `orderId=45678`

- `POST /handler/syncall`: This is a special endpoint which can be called externally to sync all the data available by the third party API. This can be used to save all the data rather than working on individual ID. This method is called once when the REST API startups to save all the previous data. No input data is required.

## Deployment Steps

Make sure that the following dependencies are installed/present on the server where the code will be compiled and run:

1. Java 1.8+
2. Availability of Internet to Call the third party API.

### Compile the jar

1. Clone this repository.

2. Change to the driectory by running following command:
	`cd <REPO_CLONE_PATH>/ZLMT-Webhook`

3. Run the following commands to build the jar:
	```
	chmod +x mvnw
	./mvnw clean install		
	```
4. Once the above commands is successfull, it will generate the jar under target folder, called 'zlmt-webhook-handler-1.0.0.jar'.


### Run the Web API

1. On the server where this will run create a working folder eg 'webhook_handler'.
2. Place the jar file created above under this folder.
2. Under this folder create a folder called `config`.
3. Copy the file `/zlmt-webhook-handler/src/main/resources/application.properties` to this config folder.
4. Edit the application.properties file to update the My SQL configuration parameters.
5. Run the jar as a background service. To Know more about how to run a java app as a service on indows please visit: [Run Java As Service On Windows](https://www.baeldung.com/spring-boot-app-as-a-service#1-windows-service-wrapper)

  





