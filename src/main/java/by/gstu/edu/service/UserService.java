package by.gstu.edu.service;

import by.gstu.edu.model.TempUser;
import org.springframework.stereotype.Service;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public interface UserService {
    TempUser generateTempUser(String email, String code);
}
