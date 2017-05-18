package com.laidu.bishe.utils.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by chenwen on 17/1/10.
 */
@Slf4j
public class ValidateUtil {
    private static Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            int index = 0;
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                String msg = constraintViolation.getMessage();
                if (msg != null && org.apache.commons.lang3.StringUtils.isNotEmpty(msg)){
                    message.append(msg);
                } else {
                    message.append("字段不合法");
                }
                log.error(String.format("{ %s (%s): 字段不合法 }", constraintViolation.getPropertyPath(), constraintViolation.getInvalidValue()));
                if (index != constraintViolations.size() - 1) {
                    message.append(" , ");
                }
                index++;
            }
            throw new IllegalArgumentException(message.toString());
        }
    }
}
