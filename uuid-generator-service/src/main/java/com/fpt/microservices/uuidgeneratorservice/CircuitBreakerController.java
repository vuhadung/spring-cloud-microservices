package com.fpt.microservices.uuidgeneratorservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Sample API call received!");
        ResponseEntity<String> resp = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return resp.getBody();
    }

    @GetMapping("/sample-api-circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String sampleApi_CircuitBreaker() {
        logger.info("Sample API call with circuit breaker received!");
        ResponseEntity<String> resp = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return resp.getBody();
    }

    @GetMapping("/sample-api-rate-limiter")
    @RateLimiter(name = "default")
    public String sampleApi_RateLimiter() {
        logger.info("Sample API call with rate limiter received!");
        return "Sample API";
    }

    @GetMapping("/sample-api-bulkhead")
    @Bulkhead(name = "sample-api")
    public String sampleApi_Bulkhead() {
        logger.info("Sample API call with bulkhead received!");
        return "Sample API";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }

}
