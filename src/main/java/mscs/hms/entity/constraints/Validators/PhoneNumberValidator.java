package mscs.hms.entity.constraints.Validators;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mscs.hms.entity.constraints.PhoneNumberConstraint;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", value);
    }
    
}
