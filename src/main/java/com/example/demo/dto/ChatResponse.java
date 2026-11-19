package com.example.demo.dto;

import java.util.List;
import com.example.demo.entity.Book;

public record ChatResponse(
        String response,
        List<Book> internalBooks,
        List<Book> externalBooks) {
}