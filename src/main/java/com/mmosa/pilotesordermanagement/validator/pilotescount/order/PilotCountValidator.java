package com.mmosa.pilotesordermanagement.validator.pilotescount.order;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PilotCountValidator implements ConstraintValidator<ValidPilotCount, Integer> {
    @Override
    public void initialize(ValidPilotCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == 5 || value == 10 || value == 15;
    }
}
