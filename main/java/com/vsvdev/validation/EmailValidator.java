package com.vsvdev.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vsvdev.annotations.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>{
@Override
	public void initialize(ValidEmail constraintAnnotation) {
		
	}


private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + 
"[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return (validateEmail(email));
	}
	

	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
