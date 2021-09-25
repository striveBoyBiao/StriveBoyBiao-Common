package com.zizhuling.common.annotation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class DictValidator implements ConstraintValidator<Dict, String> {
    private Set<String> dict;
    private String value;

    @Override
    public void initialize(Dict dictData) {
        value = dictData.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return CollectionUtils.isEmpty(dict) || StringUtils.isEmpty(s) || dict.contains(s);
    }
}
