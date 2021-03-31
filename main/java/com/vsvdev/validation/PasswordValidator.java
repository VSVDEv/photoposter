package com.vsvdev.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vsvdev.annotations.PasswordMatches;

import com.vsvdev.payload.SignUpRequest;

public class PasswordValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		SignUpRequest request = (SignUpRequest) obj;
		return request.getPassword().equals(request.getConfirmPassword());
	}

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		
	}

}
