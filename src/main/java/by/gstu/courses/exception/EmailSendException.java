package by.gstu.courses.exception;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public class EmailSendException extends RuntimeException {
    public EmailSendException() {
    }

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendException(Throwable cause) {
        super(cause);
    }
}
