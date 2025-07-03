package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.listAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable("id") String id) {
        return service.findBookById(id);
    }
    
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable("author") String author) throws IOException {
    	List<Book> result = null;
        try {
			result = service.findBooksByAuthor(author);
		} 
        catch (ElasticsearchException elex) {
			elex.printStackTrace();
			throw elex;
		} 
        catch (IOException ioex) {
			ioex.printStackTrace();
			throw ioex;
		}
        return result;
    }
    
    @GetMapping("/method/{method}")
    public ResponseEntity<?> getAllBooksBySortingMethod(@PathVariable("method") String method) {
    	if("L".equalsIgnoreCase(method)) {
    		return ResponseEntity.ok(service.listAllBooks());
    	}
    	else if("S".equalsIgnoreCase(method)) {
    			return ResponseEntity.ok(service.setAllBooks());
    	}
	    return ResponseEntity.ok(service.mapAllBooks());
    
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        book.setId(id);
        return service.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        service.removeBook(id);
    }
}

