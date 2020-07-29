package com.company.planner2.entity.validator;

import com.haulmont.cuba.core.global.validation.groups.UiCrossFieldChecks;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SessionIntersectionValidator.class)
public @interface CheckSessionIntersection {
    String message() default "It intersects with some other session";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}