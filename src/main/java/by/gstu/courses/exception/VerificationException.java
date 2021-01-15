package by.gstu.courses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VerificationException extends RuntimeException {
    public VerificationException() {
    }

    public VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationException(Throwable cause) {
        super(cause);
    }
}
