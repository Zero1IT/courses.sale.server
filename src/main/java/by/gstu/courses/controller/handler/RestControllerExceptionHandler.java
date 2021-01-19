package by.gstu.courses.controller.handler;

import by.gstu.courses.controller.handler.response.DataExceptionResponse;
import by.gstu.courses.exception.DataValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@ControllerAdvice
@ResponseBody
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public DataExceptionResponse dataValidationExceptionHandler(DataValidationException e) {
        final BindingResult bindingResult = e.getBindingResult();
        return DataExceptionResponse.from(bindingResult.getFieldErrors());
    }
}
