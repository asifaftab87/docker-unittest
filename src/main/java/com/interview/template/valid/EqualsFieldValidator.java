package com.interview.template.valid;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualsFieldValidator implements ConstraintValidator<EqualsField, String> {

    private EqualsField equalsField;

    @Override
    public void initialize(EqualsField equalsField) {
        System.out.println("------------initialize--------------------");
        this.equalsField = equalsField;
    }

    @Override
    public boolean isValid(String thisField, ConstraintValidatorContext arg1) {

        System.out.println("------------isValid--------------------: "+thisField);
        return false;
        //my validation
    }

}