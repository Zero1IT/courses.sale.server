package by.gstu.courses.controllers;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * createdAt: 3/28/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public final class AccountInfo {

    private AccountInfo() {}

    public static long getCurrentUserId() {
        return (long) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}