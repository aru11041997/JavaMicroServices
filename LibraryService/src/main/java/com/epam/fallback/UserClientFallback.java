package com.epam.fallback;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.epam.models.User;
import com.epam.services.UserServiceClient;

@Component
public class UserClientFallback implements UserServiceClient {

	@Override
	public ResponseEntity<List<User>> getUsers() {
		User user= new User("Test","Test","Test");
		user.setId(1);
		return ResponseEntity.of(Optional.of(Arrays.asList(user)));
	}

	@Override
	public ResponseEntity<User> getUserById(long id) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<User> addUser(User user) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<User> updateUser(User user) {
		return ResponseEntity.status(503).build();
	}

	@Override
	public ResponseEntity<Void> deleteUser(long id) {
		return ResponseEntity.status(503).build();
	}

}
