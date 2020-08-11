package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entities.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Integer> {

}
