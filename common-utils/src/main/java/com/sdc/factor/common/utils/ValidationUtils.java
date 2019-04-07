package com.sdc.factor.common.utils;

import com.sdc.factor.common.error.BizException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

/**
 * 参数校验工具类
 *
 * @author Harway
 * @version v1.0
 * @since 2019/1/21
 */
public class ValidationUtils {

    /**
     * 使用hibernate的注解来进行验证
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, groups);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            ConstraintViolation<T> constraintViolation = constraintViolations.iterator().next();
            throw new BizException(Integer.valueOf(constraintViolation.getMessageTemplate()), Collections.singletonList(constraintViolation.getPropertyPath()));
        }
    }
}
