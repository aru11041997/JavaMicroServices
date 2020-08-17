package com.talk2amareswaran.projects.authserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talk2amareswaran.projects.authserver.pojo.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

}
