package com.zlmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

	@Autowired
	Controller controller;

	@Override
	public void run(String... args) throws Exception {

		controller.processAll();

	}
}