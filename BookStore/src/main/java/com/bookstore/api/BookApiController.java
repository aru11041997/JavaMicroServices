package com.bookstore.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.BookEntity;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookApiController {

	@Autowired
	BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> bookDTOs=bookService.getAllBooks();
		return ResponseEntity.ok(bookDTOs);
	}
	
	@PostMapping("/addbook")
	public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
		BookEntity bookEntity=bookService.addBook(bookDTO);
		if(bookEntity==null) {
			return ResponseEntity.badRequest().build();
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(bookEntity.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity getBookById(@PathVariable int bookId){
		BookDTO bookDTO = bookService.getBookId(bookId);
		if(bookDTO.getId()==0) {
			return ResponseEntity.badRequest().build();
		}
		Link selfLink=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookApiController.class).getBookById(bookId)).withSelfRel();
		Link allLink=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookApiController.class).getAllBooks()).withRel("all-bok");
		EntityModel<BookDTO> bookModel=new EntityModel<>(bookDTO, selfLink,allLink);
		return ResponseEntity.ok(bookModel);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updateBook(@RequestBody BookDTO bookDTO) {
		BookEntity bookEntity=bookService.addBook(bookDTO);
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
