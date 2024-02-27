package com.fpt.microservices.greetingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "uuid-generator")
public interface UUIDGeneratorProxy {

    @GetMapping("/generate-new")
    String retrieveUUID();
}
