package com.fpt.microservices.greetingservice.controller;

import com.fpt.microservices.greetingservice.bean.Book;
import com.fpt.microservices.greetingservice.service.UUIDGeneratorProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private UUIDGeneratorProxy uuidGeneratorProxy;

    @Autowired
    public BookController(UUIDGeneratorProxy uuidGeneratorProxy) {
        this.uuidGeneratorProxy = uuidGeneratorProxy;
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        Book book = new Book(uuidGeneratorProxy.retrieveUUID(), "Spring Cloud in Action");
        return List.of(book);
    }
}
