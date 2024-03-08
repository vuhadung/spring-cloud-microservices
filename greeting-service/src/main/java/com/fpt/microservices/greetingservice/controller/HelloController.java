package com.fpt.microservices.greetingservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private Environment environment;

    @Autowired
    public HelloController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<?> hello() {
    	logger.info("Received a request to /hello api");
        String msg = "Hello from greeting-service port " + environment.getProperty("server.port");
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

}
