package com.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dto.BookDto;
import com.book.entity.BookEntity;
import com.book.repository.BookRepo;



@Service
public class BookService {

	@Autowired
	BookRepo bookRepo;
	
	private static Logger logger=LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<BookDto> getAllBooks() {
		logger.info("Get All Book Meth");
		List<BookEntity> bookEntities= bookRepo.findAll();
		return bookEntities.stream()
				.map(bookentity->modelMapper.map(bookentity, BookDto.class))
				.collect(Collectors.toList());
	}

	public BookEntity addBook(BookDto bookDto) {
		BookEntity bookEntity=modelMapper.map(bookDto,BookEntity.class);
		return bookRepo.save(bookEntity);
	}

	public BookDto getBookId(int bookId) {
		BookEntity  bookEntity= bookRepo.findById(bookId).map(book->book).orElse(new BookEntity());	
		return modelMapper.map(bookEntity,BookDto.class);
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
