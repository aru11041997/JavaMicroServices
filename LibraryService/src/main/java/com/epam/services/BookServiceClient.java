package com.epam.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.fallback.BookClientFallback;
import com.epam.models.Book;

@FeignClient(name = "BookService",fallback = BookClientFallback.class,path = "/book")
@Component
public interface BookServiceClient {

	@GetMapping
	ResponseEntity<List<Book>> getBooks();

	@GetMapping("/{id}")
	ResponseEntity<Book> getBookById(@PathVariable int id);

	@PostMapping
	ResponseEntity<Book> addBook(@RequestBody Book book);

	@PutMapping("/update")
	ResponseEntity<Book> updateBook(@RequestBody Book book);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteBook(@PathVariable int id);
}
