package by.gstu.edu.service;

import by.gstu.edu.model.TempUser;
import org.springframework.stereotype.Service;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface EmailService {
    void sendActivationLink(String email, String link, TempUser user);
}
