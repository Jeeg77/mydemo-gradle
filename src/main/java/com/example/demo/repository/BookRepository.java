package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.demo.entity.Book;

public interface BookRepository extends ElasticsearchRepository<Book, String> {
}

