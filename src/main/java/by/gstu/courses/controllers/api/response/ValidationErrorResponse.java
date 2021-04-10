package by.gstu.courses.controllers.api.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * createdAt: 4/10/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationDetails> errors;

    @Getter
    @Setter
    public static class ValidationDetails {
        private String code;
        private String message;
        private String field;
    }
}
