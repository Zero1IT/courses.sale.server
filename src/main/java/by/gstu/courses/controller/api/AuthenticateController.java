package by.gstu.courses.controller.api;

import by.gstu.courses.dto.AuthenticationDto;
import by.gstu.courses.exception.DataValidationException;
import by.gstu.courses.exception.JwtAuthenticationException;
import by.gstu.courses.model.User;
import by.gstu.courses.provider.JwtTokenProvider;
import by.gstu.courses.service.AuthenticateService;
import by.gstu.courses.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * !!!
 * @see by.gstu.courses.config.security.JwtAuthenticationFilter handles '/api/auth/login' route
 * !!!
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

    private final EmailService emailService;
    private final AuthenticateService authenticateService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${host.domain}")
    private String domain;
    @Value("${const.verify_link_format}")
    private String verifyLinkFormat;
    @Value("${const.activate_link_format}")
    private String activateLinkFormat;

    public AuthenticateController(EmailService emailService, AuthenticateService authenticateService, JwtTokenProvider jwtTokenProvider) {
        this.emailService = emailService;
        this.authenticateService = authenticateService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("auto")
    @ResponseStatus(HttpStatus.CREATED)
    public void autoRegistration(@RequestParam String email) {
        final String code = UUID.randomUUID().toString();
        final String link = String.format(activateLinkFormat, domain, code);
        emailService.sendActivationLink(email, link, authenticateService.generateTempUser(email, code));
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
        // TODO: duplicate activation link if not confirmed
        final String code = UUID.randomUUID().toString();
        final String link = String.format(verifyLinkFormat, domain, code);
        if (dto.getPassword() != null && dto.getPassword().equals(dto.getRepeatedPassword())) {
            final User user = new User();
            user.setName(dto.getName());
            user.setLastname(dto.getLastname());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setPhone(dto.getPhone());
            emailService.sendActivationLink(user.getEmail(), link, null);
            authenticateService.registrationUser(user, code);
        } else {
            throw new DataValidationException("repeatedPassword", "passwords are not equal"); // TODO: user resource
        }
    }
}
