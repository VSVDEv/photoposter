package com.vsvdev.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



import com.vsvdev.validation.PasswordValidator;

@Target({ ElementType.TYPE,ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordMatches {
	String message() default "Passwords aren't equals";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
