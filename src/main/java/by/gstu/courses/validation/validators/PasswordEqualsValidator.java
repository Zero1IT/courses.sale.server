package by.gstu.courses.validation.validators;

import by.gstu.courses.dto.AuthenticationDto;
import by.gstu.courses.validation.constraints.PasswordEqualsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * createdAt: 4/10/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class PasswordEqualsValidator implements ConstraintValidator<PasswordEqualsConstraint, Object> {
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof AuthenticationDto) {
            AuthenticationDto dto = (AuthenticationDto) obj;
            return dto.getPassword().equals(dto.getConfirmedPassword());
        }
        return false;
    }
}
