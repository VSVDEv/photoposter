package com.vsvdev.services;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vsvdev.dto.UserDTO;
import com.vsvdev.entity.User;
import com.vsvdev.entity.enums.Role;
import com.vsvdev.exception.UserExistsException;
import com.vsvdev.payload.SignUpRequest;
import com.vsvdev.repository.UserRepository;



@Service
public class UserService {
	public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

private final UserRepository repository;
private final BCryptPasswordEncoder encoder;
@Autowired
public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
	this.repository = repository;
	this.encoder = encoder;
}
public User createUser(SignUpRequest userIn) {
	User user = new User();
	user.setEmail(userIn.getEmail());
	user.setName(userIn.getFirstName());
	user.setLastname(userIn.getLastName());
	user.setUsername(userIn.getUserName());
	user.setPassword(encoder.encode(userIn.getPassword()));
	user.getRole().add(Role.ROLE_USER);
	try {
		LOG.info("Saving user", userIn.getEmail());
		return repository.save(user);
	}catch(Exception ex) {
		LOG.error("Error during registtration ", ex.getMessage());
		throw new UserExistsException("The user "+user.getUsername()+ " already exist.");
	}
}

public User updateUser(UserDTO userDTO, Principal principal) {
	User user = getUserByPrincipal(principal);
	user.setName(userDTO.getFirstName());
	user.setLastname(userDTO.getLastName());
	user.setBiography(userDTO.getBiography());
	return repository.save(user);
}
private User getUserByPrincipal(Principal principal) {
	String userName = principal.getName();
	return repository.findUserByUsername(userName)
			.orElseThrow(()->new UsernameNotFoundException("Username not found "+userName));
}
public User getCurrentUser(Principal principal) {
	return getUserByPrincipal(principal);
}
public User getUserById(long userId) {
		return repository.findById(userId)
				.orElseThrow(()-> new UsernameNotFoundException("User not found "));
}
}
