package com.flybase.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flybase.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
