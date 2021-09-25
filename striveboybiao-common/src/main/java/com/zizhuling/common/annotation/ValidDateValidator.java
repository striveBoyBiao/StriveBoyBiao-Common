package com.zizhuling.common.annotation;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ValidDateValidator implements ConstraintValidator<ValidDate, Date> {

    @Override
    public void initialize(ValidDate validDate) {
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value.before(new DateTime()) && value.after(DateUtil.parseDate("1970-01-01"));
    }
}
