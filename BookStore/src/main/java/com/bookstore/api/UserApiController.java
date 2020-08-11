package com.bookstore.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookstore.dto.UserDTO;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserApiController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDTO> userDTOs=userService.getAllUser();
		return ResponseEntity.ok(userDTOs);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
		UserDTO userDTO=userService.getUserById(userId);
		if(userDTO.getId()==0) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		UserDTO userDto = userService.addUser(userDTO);
		if(userDto.getId()==0) {
			return ResponseEntity.badRequest().build();
		}
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		boolean isDeleted=userService.deleteUser(userId);
		if(!isDeleted)
			return ResponseEntity.badRequest().build();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> editUser(@RequestBody UserDTO userDTO) {
		UserDTO userDto = userService.addUser(userDTO);
		if(userDto.getId()==0) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

}
