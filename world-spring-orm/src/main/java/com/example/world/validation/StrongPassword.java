package com.example.world.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Size(min=8)
@Pattern.List({
		@Pattern(regexp = "^.*\\d+.*$", message = "{validation.strongPassword2}"),
		@Pattern(regexp = "^.*[-_]+.*$", message = "{validation.strongPassword3}") })
@Constraint(validatedBy = {})
public @interface StrongPassword {
	String message() default "{validation.strongPassword1}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
