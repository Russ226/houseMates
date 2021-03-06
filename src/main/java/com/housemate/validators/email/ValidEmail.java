package com.housemate.validators.email;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ValidEmail {
    public String message() default "Incorrect email format!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
