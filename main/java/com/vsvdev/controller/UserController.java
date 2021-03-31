package com.vsvdev.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsvdev.dto.UserDTO;
import com.vsvdev.entity.User;
import com.vsvdev.facade.UserFacade;
import com.vsvdev.services.UserService;
import com.vsvdev.validation.ResponseErrorValidation;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	private final UserService userService;
	private final UserFacade facade;
	private final ResponseErrorValidation responseErrorValidation;

	@Autowired
	public UserController(UserService userService, UserFacade facade, ResponseErrorValidation responseErrorValidation) {
		this.userService = userService;
		this.facade = facade;
		this.responseErrorValidation = responseErrorValidation;
	}

	@GetMapping("/")
	public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
		User user = userService.getCurrentUser(principal);
		UserDTO userDTO = facade.userToUserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId) {
		User user = userService.getUserById(Long.parseLong(userId));
		UserDTO userDTO = facade.userToUserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

@PostMapping("/update")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult result,
			Principal principal) {
		ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(result);
		if (!ObjectUtils.isEmpty(errors)) {
			return errors;
		}
		User user = userService.updateUser(userDTO, principal);
		UserDTO uodatedUser = facade.userToUserDTO(user);
		return new ResponseEntity<>(uodatedUser, HttpStatus.OK);
	}
}
