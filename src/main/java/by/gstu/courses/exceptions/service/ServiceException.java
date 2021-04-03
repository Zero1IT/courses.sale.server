package by.gstu.courses.exceptions.service;

/**
 * createdAt: 3/21/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
