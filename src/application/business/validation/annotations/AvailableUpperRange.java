package application.business.validation.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.business.validation.validators.AvailableValidator;

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = AvailableValidator.class)
public @interface AvailableUpperRange {
	
	int years() default 0;
	String message() default "Period out of range";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	

}
