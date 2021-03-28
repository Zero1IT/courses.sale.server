package by.gstu.courses.controller;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * createdAt: 3/28/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public final class Utils {

    private Utils() {}

    public static long getCurrentUserId() {
        return (long) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}