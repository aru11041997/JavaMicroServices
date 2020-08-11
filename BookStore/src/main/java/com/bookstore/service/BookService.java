package com.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entities.BookEntity;
import com.bookstore.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<BookDTO> getAllBooks() {
		List<BookEntity> bookEntities= bookRepo.findAll();
		return bookEntities.stream()
				.map(bookentity->modelMapper.map(bookentity, BookDTO.class))
				.collect(Collectors.toList());
	}

	public BookEntity addBook(BookDTO bookDTO) {
		BookEntity bookEntity=modelMapper.map(bookDTO,BookEntity.class);
		return bookRepo.save(bookEntity);
	}

	public BookDTO getBookId(int bookId) {
		BookEntity  bookEntity= bookRepo.findById(bookId).map(book->book).orElse(new BookEntity());	
		return modelMapper.map(bookEntity,BookDTO.class);
	}

	public boolean deleteBook(int bookId) {
		boolean isDeleted;
		try {
			bookRepo.deleteById(bookId);
			isDeleted=true;
		}catch (IllegalArgumentException e) {
			isDeleted=false;
		}
		return isDeleted;
	}
	
}
