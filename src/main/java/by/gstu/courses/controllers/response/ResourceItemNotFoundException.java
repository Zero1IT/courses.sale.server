package by.gstu.courses.controllers.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * createdAt: 2/3/2021
 * project: CourseSaleServer
 *
 * Used for throw exception when entity not found
 *
 * @author Alexander Petrushkin
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceItemNotFoundException extends RuntimeException {
    public ResourceItemNotFoundException() {
    }

    public ResourceItemNotFoundException(String message) {
        super(message);
    }

    public ResourceItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
