package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
