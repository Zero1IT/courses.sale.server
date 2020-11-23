package by.gstu.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Controller
public class HomeController {
    @GetMapping("/verify/{code}")
    public String emailRegistrationVerification(@PathVariable String code) {
        return "verify-page";
    }
}
