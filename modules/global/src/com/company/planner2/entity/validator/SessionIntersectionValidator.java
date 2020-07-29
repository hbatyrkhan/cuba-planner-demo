package com.company.planner2.entity.validator;

import com.company.planner2.entity.Session;
import com.company.planner2.service.SessionService;
import com.haulmont.cuba.core.global.AppBeans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SessionIntersectionValidator implements ConstraintValidator<CheckSessionIntersection, Session> {
    private static Logger logger = LoggerFactory.getLogger(Session.class);
    @Override
    public void initialize(CheckSessionIntersection constraintAnnotation) {

    }

    @Override
    public boolean isValid(Session value, ConstraintValidatorContext context) {
        SessionService sessionService = AppBeans.get(SessionService.class);
        logger.debug("Validating: " + value);
        return sessionService.valid(value);
    }
}