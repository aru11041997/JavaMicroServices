package com.book.apicontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.book.dto.BookDto;
import com.book.entity.BookEntity;
import com.book.service.BookService;


@RestController
@RequestMapping("/book")
public class BookApiController {

	@Autowired
	BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<BookDto> bookDTOs=bookService.getAllBooks();
		return ResponseEntity.ok(bookDTOs);
	}
	
	@PostMapping("/addbook")
	public ResponseEntity<Void> addBook(@RequestBody BookDto bookDTO) {
		BookEntity bookEntity=bookService.addBook(bookDTO);
		if(bookEntity==null) {
			return ResponseEntity.badRequest().build();
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(bookEntity.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity getBookById(@PathVariable int bookId){
		BookDto bookDto = bookService.getBookId(bookId);
		if(bookDto.getId()==0) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(bookDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {
		BookEntity bookEntity=bookService.addBook(bookDto);
		if(bookEntity==null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable int bookId){
		boolean isDeleted=bookService.deleteBook(bookId);
		if(!isDeleted)
			return ResponseEntity.badRequest().build();
		
		return ResponseEntity.noContent().build();
	}
}
