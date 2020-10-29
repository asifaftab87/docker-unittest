package com.interview.template.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

import com.interview.template.en.BlackListUsername;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy=EqualsFieldValidator.class)
@Documented
public @interface EqualsField {

    public String field();

    String message() default "value not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
