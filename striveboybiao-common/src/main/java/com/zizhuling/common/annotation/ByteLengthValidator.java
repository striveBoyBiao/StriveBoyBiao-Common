package com.zizhuling.common.annotation;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.invoke.MethodHandles;

public class ByteLengthValidator implements ConstraintValidator<ByteLength, String> {

    private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());

    private int min;
    private int max;

    @Override
    public void initialize(ByteLength parameters) {
        min = parameters.min();
        max = parameters.max();
        validateParameters();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // oracle
//        int length = value.getBytes(Charset.forName("gbk")).length;
        // MYSQL
        int length = value.length();
        return length >= min && length <= max;
    }

    private void validateParameters() {
        if (min < 0) {
            throw LOG.getMinCannotBeNegativeException();
        }
        if (max < 0) {
            throw LOG.getMaxCannotBeNegativeException();
        }
        if (max < min) {
            throw LOG.getLengthCannotBeNegativeException();
        }
    }

}
