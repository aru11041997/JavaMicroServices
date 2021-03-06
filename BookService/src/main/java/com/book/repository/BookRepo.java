package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Integer> {

}
