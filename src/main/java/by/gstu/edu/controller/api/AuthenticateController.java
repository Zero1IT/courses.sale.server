package by.gstu.edu.controller.api;

import by.gstu.edu.dto.AuthenticationDto;
import by.gstu.edu.exception.JwtAuthenticationException;
import by.gstu.edu.model.User;
import by.gstu.edu.provider.JwtTokenProvider;
import by.gstu.edu.service.AuthenticateService;
import by.gstu.edu.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

    private final EmailService emailService;
    private final AuthenticateService authenticateService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${host.link}")
    private String domain;
    @Value("${const.verify_link_format}")
    private String verifyLinkFormat;

    public AuthenticateController(EmailService emailService, AuthenticateService authenticateService, JwtTokenProvider jwtTokenProvider) {
        this.emailService = emailService;
        this.authenticateService = authenticateService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("auto")
    @ResponseStatus(HttpStatus.CREATED)
    public void autoRegistration(@RequestParam String email) {
        emailService.sendActivationLink(email, null, authenticateService.generateTempUser(email));
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(@RequestBody String token) { // NOSONAR
        try {
            return ResponseEntity.ok(jwtTokenProvider.refreshToken(token));
        } catch (JwtAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody AuthenticationDto dto) {
        String code = UUID.randomUUID().toString();
        String link = String.format(verifyLinkFormat, domain, code);
        User user = new User();
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        emailService.sendActivationLink(user.getEmail(), link, null);
        authenticateService.registrationUser(user, code);
    }
}
