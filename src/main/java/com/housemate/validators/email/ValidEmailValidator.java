package com.housemate.validators.email;

import com.housemate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {


    @Override
    public void initialize(ValidEmail validEmail) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
       return email.contains("@");
    }
}
