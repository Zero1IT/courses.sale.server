package by.gstu.courses.exception;

import org.springframework.validation.BindingResult;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class DataValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public DataValidationException(BindingResult bindingResult) {
        this("", bindingResult);
    }

    /**
     * Field name (if exists) in the message, must be wrapped in square brackets
     * @param message - error message, will be represent in error object with key __error
     */
    public DataValidationException(String message) {
        this(message, null);
    }

    public DataValidationException(String message, BindingResult bindingResult) {
        this(message, null, bindingResult);
    }

    public DataValidationException(String message, Throwable cause, BindingResult bindingResult) {
        super(message, cause);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
