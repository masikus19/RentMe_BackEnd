package application.business.validation.validators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.business.validation.annotations.AvailableUpperRange;

public class AvailableValidator implements ConstraintValidator<AvailableUpperRange, LocalDate>{

	private int years;
	
	@Override
	public void initialize(AvailableUpperRange annotation) {
		this.years = annotation.years();
	}
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		LocalDate now = LocalDate.now();
		
		return value.isBefore(now.plusYears(years));
	}

}
