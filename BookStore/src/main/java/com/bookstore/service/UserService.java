package com.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dto.UserDTO;
import com.bookstore.entities.UserEntity;
import com.bookstore.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	public List<UserDTO> getAllUser() {
		List<UserEntity> userEntities=userRepo.findAll();
		return userEntities.stream().map(userEntity->modelMapper.map(userEntity, UserDTO.class)).collect(Collectors.toList());
	}

	public UserDTO getUserById(int userId) {
		UserEntity userEntity = userRepo.findById(userId).map(user->user).orElse(new UserEntity());	
		return modelMapper.map(userEntity,UserDTO.class);
	}

	public UserDTO addUser(UserDTO userDTO) {
		UserEntity userEntity=userRepo.save(modelMapper.map(userDTO,UserEntity.class));
		if(userEntity==null) {
			return new UserDTO();
		}
		
		return modelMapper.map(userEntity,UserDTO.class);
	}

	public boolean deleteUser(int userId) {
		boolean isDeleted;
		try {
			userRepo.deleteById(userId);
			isDeleted=true;
		}catch (IllegalArgumentException e) {
			isDeleted=false;
		}
		return isDeleted;
	}

}
