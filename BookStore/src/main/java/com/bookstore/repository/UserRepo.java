package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
