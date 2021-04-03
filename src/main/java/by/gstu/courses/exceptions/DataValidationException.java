package by.gstu.courses.exceptions;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class DataValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;
    private Map<String, String> errorsMap;

    public DataValidationException(BindingResult bindingResult) {
        this("", bindingResult);
    }

    public DataValidationException(String message) {
        this(message, (BindingResult)null);
    }

    public DataValidationException(String field, String message) {
        this("");
        errorsMap = new HashMap<>();
        errorsMap.put(field, message);
    }

    public DataValidationException(String message, BindingResult bindingResult) {
        this(message, null, bindingResult);
    }

    public DataValidationException(String message, Throwable cause, BindingResult bindingResult) {
        super(message, cause);
        this.bindingResult = bindingResult;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
