package customer.api.v1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<genderValidation, String> {

    @Override
    public void initialize(genderValidation gen) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value.equalsIgnoreCase("male")||value.equalsIgnoreCase("female"));
    }
}

