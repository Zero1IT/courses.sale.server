package by.gstu.courses.controllers.api;

import by.gstu.courses.domain.User;
import by.gstu.courses.dto.AuthenticationDto;
import by.gstu.courses.exceptions.JwtAuthenticationException;
import by.gstu.courses.providers.JwtTokenProvider;
import by.gstu.courses.services.AuthenticateService;
import by.gstu.courses.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

    private final EmailService emailService;
    private final AuthenticateService authenticateService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${app.host.domain}")
    private String domain;
    @Value("${app.const.verify_link_format}")
    private String verifyLinkFormat;
    @Value("${app.const.activate_link_format}")
    private String activateLinkFormat;

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
    public void registration(@RequestBody @Valid AuthenticationDto dto) {
        final String code = UUID.randomUUID().toString();
        final String link = String.format(verifyLinkFormat, domain, code);
        final User user = new User();
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        authenticateService.registrationUser(user, code);
        emailService.sendActivationLink(user.getEmail(), link, null);
    }
}
