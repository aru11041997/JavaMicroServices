package com.epam.fallback;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.epam.models.Book;
import com.epam.services.BookServiceClient;

@Component
public class BookClientFallback implements BookServiceClient{

	@Override
	public ResponseEntity<List<Book>> getBooks() {
		Book book= new Book("Test","Test");
		book.setId(1);
		return ResponseEntity.of(Optional.of(Arrays.asList(book)));
	}

	@Override
	public ResponseEntity<Book> getBookById(int id) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<Book> addBook(Book book) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<Book> updateBook(Book book) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<Void> deleteBook(int id) {
		return ResponseEntity.status(503).build();
	}

}
