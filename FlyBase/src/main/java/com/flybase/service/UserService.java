package com.flybase.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flybase.dto.UserDto;
import com.flybase.entities.UserEntity;
import com.flybase.respository.UserRepo;



@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	

	public List<UserDto> getAllUser() {
		List<UserEntity> userEntities=userRepo.findAll();
		return userEntities.stream().map(userEntity->modelMapper.map(userEntity, UserDto.class)).collect(Collectors.toList());
	}

	public UserDto getUserById(int userId) {
		UserEntity userEntity = userRepo.findById(userId).map(user->user).orElse(new UserEntity());	
		return modelMapper.map(userEntity,UserDto.class);
	}

	public UserDto addUser(UserDto userDTO) {
		UserEntity userEntity=userRepo.save(modelMapper.map(userDTO,UserEntity.class));
		if(userEntity==null) {
			return new UserDto();
		}
		
		return modelMapper.map(userEntity,UserDto.class);
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
