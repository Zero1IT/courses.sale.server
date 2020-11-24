package by.gstu.edu.controller.api;

import by.gstu.edu.service.AuthenticateService;
import by.gstu.edu.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.UUID;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

    private final EmailService emailService;
    private final AuthenticateService authenticateService;

    @Value("${host.link}")
    private String domain;

    public AuthenticateController(EmailService emailService, AuthenticateService authenticateService) {
        this.emailService = emailService;
        this.authenticateService = authenticateService;
    }

    @PostMapping("auto")
    @ResponseStatus(HttpStatus.CREATED)
    public void autoRegistration(@RequestParam @Pattern(regexp = "") String email) {
        String code = UUID.randomUUID().toString();
        String link = String.format("%s/verify/%s", domain, code);
        emailService.sendActivationLink(email, link, authenticateService.generateTempUser(email, code));
    }
}
