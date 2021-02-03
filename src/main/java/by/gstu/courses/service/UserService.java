package by.gstu.courses.service;

import by.gstu.courses.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserService extends DefaultService<User> {
    User giveLecturerPermissions(String userEmail);
    User giveLecturerPermissions(Long id);
    List<User> getUsersList(int page, int limit);
    List<User> getLecturerList(int page, int limit);
    Page<User> getUsersPage(Pageable pageable, String email);
    Page<User> getLecturePage(Pageable pageable, String email);
    Page<User> getPageByEmail(Pageable pageable, String email);
    User changeRole(Long userId, String newRoleName);
}
