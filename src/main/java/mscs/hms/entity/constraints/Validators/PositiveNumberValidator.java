package mscs.hms.entity.constraints.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mscs.hms.entity.constraints.PositiveNumberConstraint;

public class PositiveNumberValidator implements ConstraintValidator<PositiveNumberConstraint, Number> {

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        return value != null && value.doubleValue() > 0;
    }
    
}
