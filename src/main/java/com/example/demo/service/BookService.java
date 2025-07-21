package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.utils.SortingItems;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private ElasticsearchClient elasticsearchClient;
	
	public List<Book> listAllBooks() {
		return SortingItems.mergeBooks(repository.findAll());
	}
	
	public Set<Book> setAllBooks() {
		return SortingItems.setBooks(repository.findAll());
	}
	
	public Map<Integer, Book> mapAllBooks() {
		return SortingItems.mapBooks(repository.findAll());
	}
	
	public Optional<Book> findBookById(String id) {
        return repository.findById(id);
    }
	
	public Book createBook(Book book) {
        return repository.save(book);
    }
	
    public Book updateBook(Book book) {
        return repository.save(book);
    }
    
    public void removeBook(String id) {
        repository.deleteById(id);
    }
    
    public List<Book> findBooksByAuthor(String author) throws ElasticsearchException, IOException {
    	
    	SearchResponse<Book> response = 
    		elasticsearchClient.search(s -> 
	    		s.index("books").query(q -> 
	    			q.match(m -> 
	    				m.field("author").query(author)
	    			)
	    		), Book.class
		    );
    	
    	List<Book> books = response.hits().hits().stream()
    		    .map(Hit::source)
    		    .collect(Collectors.toList());
    	
    	return books;
    }

}
