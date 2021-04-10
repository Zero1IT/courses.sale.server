package by.gstu.courses.validation.constraints;

import by.gstu.courses.validation.validators.PasswordEqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * createdAt: 4/10/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualsValidator.class)
public @interface PasswordEqualsConstraint {
    String message() default "Passwords are not equals"; // TODO: loc message

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
