package by.gstu.courses.controller;

import by.gstu.courses.service.AuthenticateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 *  For from-root path
 *
 * @author Alexander Petrushkin
 */
@Controller
public class HomeController {

    private final AuthenticateService authenticateService;

    public HomeController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @GetMapping("/verify/{code}")
    public String emailRegistrationVerification(@PathVariable String code) {
        authenticateService.confirmVerification(code);
        return "verify-page";
    }

    @GetMapping("/activate/{code}")
    public String activateTempUserAccountVerification(@PathVariable String code) {
        authenticateService.transferTempUser(code);
        return "verify-page";
    }
}
