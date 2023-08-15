package com.mmosa.pilotesordermanagement.validator.pilotescount.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PilotCountValidator.class)
@Documented
public @interface ValidPilotCount {
    String message() default "Invalid pilot count. Choose 5, 10, or 15.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
