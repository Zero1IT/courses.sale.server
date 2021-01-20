package by.gstu.courses.service;

import by.gstu.courses.model.Lecturer;
import by.gstu.courses.model.User;

import java.util.List;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserService extends DefaultService<User> {
    Lecturer giveLecturerPermissions(String userEmail);
    List<User> getUsersList(int page, int limit);
    List<Lecturer> getLecturerList(int page, int limit);
}
