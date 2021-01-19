package by.gstu.courses.controller.handler.response;

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
public class DataExceptionResponse {

    private final Map<String, String> fieldMessage;

    public DataExceptionResponse(Map<String, String> fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public Map<String, String> getFieldMessage() {
        return fieldMessage;
    }

    public static DataExceptionResponse from(List<FieldError> fieldErrors) {
        Map<String, String> map = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new DataExceptionResponse(map);
    }
}
