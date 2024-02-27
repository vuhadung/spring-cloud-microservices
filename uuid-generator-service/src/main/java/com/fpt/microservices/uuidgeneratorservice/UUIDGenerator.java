package com.fpt.microservices.uuidgeneratorservice;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }

}
