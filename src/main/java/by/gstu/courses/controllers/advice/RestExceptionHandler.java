package by.gstu.courses.controllers.advice;

import by.gstu.courses.controllers.api.response.ErrorResponse;
import by.gstu.courses.controllers.api.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * createdAt: 4/10/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${app.debug}")
    private boolean debug;

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                           @NotNull HttpHeaders headers,
                                                                           @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {
        final String message = debug ? exception.getMessage() : ""; // TODO: message
        final List<ValidationErrorResponse.ValidationDetails> errors = exception.getBindingResult().getAllErrors().stream()
                .map(it -> mapToValidationDetails((FieldError) it))
                .collect(Collectors.toList());
        ErrorResponse response = createErrorResponse(status, request, message, errors);
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, Object body,
                                                                      @NotNull HttpHeaders headers,
                                                                      @NotNull HttpStatus status,
                                                                      @NotNull WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, SCOPE_REQUEST);
        }

        final String message = debug ? ex.getMessage() : ""; // TODO: message
        return new ResponseEntity<>(createErrorResponse(status, request, message, null), headers, status);
    }

    private ErrorResponse createErrorResponse(HttpStatus status, WebRequest request, String msg,
                                              List<ValidationErrorResponse.ValidationDetails> errors) {
        ErrorResponse errorResponse;

        if (errors == null) {
            errorResponse = new ErrorResponse();
        } else {
            errorResponse = new ValidationErrorResponse();
            ((ValidationErrorResponse)errorResponse).setErrors(errors);
        }

        errorResponse.setTimestamp(Instant.now());
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(msg);
        errorResponse.setUrl(path(request));
        return errorResponse;
    }

    private String path(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    private ValidationErrorResponse.ValidationDetails mapToValidationDetails(FieldError error) {
        final ValidationErrorResponse.ValidationDetails details = new ValidationErrorResponse.ValidationDetails();
        details.setCode(error.getCode());
        details.setMessage(error.getDefaultMessage());
        details.setField(error.getField());
        return details;
    }
}
