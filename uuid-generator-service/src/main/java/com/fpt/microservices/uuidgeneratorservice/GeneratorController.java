package com.fpt.microservices.uuidgeneratorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {

    private UUIDGenerator uuidGenerator;

    @Autowired
    public GeneratorController(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    @GetMapping("/generate-new")
    public String generateUUID() {
        return uuidGenerator.generate();
    }

}
