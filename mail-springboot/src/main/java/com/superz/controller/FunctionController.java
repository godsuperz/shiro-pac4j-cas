package com.superz.controller;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.awt.print.Book;

/**
 * @author lovez
 */
public class FunctionController {
    public Mono<ServerResponse> listBooks(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(null, Book.class);
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        return null;
    }
}
