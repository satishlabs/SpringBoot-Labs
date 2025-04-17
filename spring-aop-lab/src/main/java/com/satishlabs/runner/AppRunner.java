package com.satishlabs.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.satishlabs.service.BookService;

@Component
public class AppRunner implements CommandLineRunner {

    private final BookService bookService;

    public AppRunner(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        bookService.getBookById(101);
        try {
            bookService.throwError();
        } catch (Exception ignored) {}
    }
}

