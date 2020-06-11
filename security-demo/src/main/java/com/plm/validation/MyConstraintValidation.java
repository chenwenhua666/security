package com.plm.validation;

import com.plm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
public class MyConstraintValidation implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("init--");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("my annotation value");
        System.out.println(value);
        userService.greeting("tom");
        return false;
    }
}
