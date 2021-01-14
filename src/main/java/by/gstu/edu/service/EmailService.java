package by.gstu.edu.service;

import by.gstu.edu.model.TempUser;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface EmailService {
    // TODO: can throw auth error
    void sendActivationLink(String email, String link, TempUser tempUser);
}
