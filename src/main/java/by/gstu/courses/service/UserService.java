package by.gstu.courses.service;

import by.gstu.courses.model.Lecturer;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserService {
    Lecturer giveLecturerPermissions(String userEmail);
}
