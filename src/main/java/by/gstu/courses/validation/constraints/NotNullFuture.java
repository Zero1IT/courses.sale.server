package by.gstu.courses.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * createdAt: 4/10/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@NotNull
@Future
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
public @interface NotNullFuture {
    String message() default "{javax.validation.constraints.Future.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
