package by.gstu.courses.controllers.api.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * createdAt: 4/6/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error; // status message or any
    private String message;
    private String url;
}
