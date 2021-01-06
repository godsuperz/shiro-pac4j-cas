package com.superz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Book;

/**
 * @author lovez
 */
@RestController
public class ReactorController {

    @GetMapping("/book")
    public Flux<Book> list() {
        return null;
    }

    @GetMapping("/book/{id}")
    public Mono<Book> findById(@PathVariable String id) {
        return null;
    }
}
