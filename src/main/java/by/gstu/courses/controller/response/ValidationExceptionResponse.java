package by.gstu.courses.controller.response;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class ValidationExceptionResponse {

    private final Map<String, String> fieldMessage;

    public ValidationExceptionResponse(Map<String, String> fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public Map<String, String> getFieldMessage() {
        return fieldMessage;
    }

    public static ValidationExceptionResponse from(List<FieldError> fieldErrors) {
        Map<String, String> map = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ValidationExceptionResponse(map);
    }
}
