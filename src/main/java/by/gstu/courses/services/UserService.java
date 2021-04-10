package by.gstu.courses.services;

import by.gstu.courses.domain.User;
import by.gstu.courses.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserService {
    User giveLecturerPermissions(String userEmail);
    User giveLecturerPermissions(Long id);
    List<User> getUsersList(int page, int limit);
    List<User> getLecturerList(int page, int limit);
    Page<User> getUsersPage(Pageable pageable, String email);
    Page<User> getLecturePage(Pageable pageable, String email);
    Page<User> getPageByEmail(Pageable pageable, String email);
    User changeRole(Long userId, String newRoleName);
    List<User> getList(int page, int limit);

    Optional<User> findUserById(long currentUserId);
}
