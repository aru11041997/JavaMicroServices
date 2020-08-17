package com.talk2amareswaran.projects.authserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talk2amareswaran.projects.authserver.pojo.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserName(String userName);
}
