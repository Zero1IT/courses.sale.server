package by.gstu.courses.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * createdAt: 2/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncompatiblePermissionsException extends RuntimeException {
    public IncompatiblePermissionsException() {
    }

    public IncompatiblePermissionsException(String message) {
        super(message);
    }

    public IncompatiblePermissionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
