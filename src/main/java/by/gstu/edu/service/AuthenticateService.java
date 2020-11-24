package by.gstu.edu.service;

import by.gstu.edu.model.TempUser;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface AuthenticateService {
    TempUser generateTempUser(String email, String code);
    void confirmVerification(String code);
}
