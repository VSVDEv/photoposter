package com.vsvdev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsvdev.payload.JWTTokenSuccessResponse;
import com.vsvdev.payload.LoginRequest;
import com.vsvdev.payload.MessageResponse;
import com.vsvdev.payload.SignUpRequest;
import com.vsvdev.security.JWTTokenProvider;
import com.vsvdev.security.SecurityConstants;
import com.vsvdev.services.UserService;
import com.vsvdev.validation.ResponseErrorValidation;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

	private final JWTTokenProvider provider;
	private final AuthenticationManager manager;
	private final ResponseErrorValidation errorValidation;
	private final UserService userService;
	@Autowired
	public AuthController(JWTTokenProvider provider, AuthenticationManager manager,
			ResponseErrorValidation errorValidation, UserService userService) {
		this.provider = provider;
		this.manager = manager;
		this.errorValidation = errorValidation;
		this.userService = userService;
	}
	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest request, BindingResult result){
		ResponseEntity<Object> errors = errorValidation.mapValidationService(result);
		if(!ObjectUtils.isEmpty(errors)) {
			return errors;
		}
		Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUserName(),
				request.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = SecurityConstants.TOKEN_PREFIX + provider.generateToken(authentication);
	return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));
	}
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest request,BindingResult result){
		ResponseEntity<Object> errors = errorValidation.mapValidationService(result);
		if(!ObjectUtils.isEmpty(errors))return errors;
		userService.createUser(request);
		return ResponseEntity.ok(new MessageResponse("User registred successfully"));
	}
}
