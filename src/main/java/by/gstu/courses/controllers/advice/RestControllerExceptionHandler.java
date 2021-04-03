package by.gstu.courses.controllers.advice;

import by.gstu.courses.controllers.response.ValidationExceptionResponse;
import by.gstu.courses.exceptions.DataValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@ControllerAdvice
@ResponseBody
public class RestControllerExceptionHandler {

    private static final String ERROR_KEY = "_error";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public ValidationExceptionResponse dataValidationExceptionHandler(DataValidationException e) {
        final BindingResult bindingResult = e.getBindingResult();
        if (bindingResult != null) {
            return ValidationExceptionResponse.from(bindingResult.getFieldErrors());
        } else {
            final HashMap<String, String> map = new HashMap<>();
            map.put(ERROR_KEY, e.getMessage());
            return new ValidationExceptionResponse(map);
        }
    }
}
