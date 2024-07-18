package io.github.augustorsn.back_end_baba.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList,List>  {

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
       return value != null && !value.isEmpty();
    }
    
}
