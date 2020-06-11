package com.plm.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MyConstraintValidation.class})
public @interface MyConstraint {
    String message() default "{com.plm.validation.MyConstraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
