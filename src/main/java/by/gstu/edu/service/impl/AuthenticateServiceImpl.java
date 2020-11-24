package by.gstu.edu.service.impl;

import by.gstu.edu.exception.VerificationException;
import by.gstu.edu.model.TempUser;
import by.gstu.edu.model.User;
import by.gstu.edu.model.VerificationCode;
import by.gstu.edu.repository.UserRepository;
import by.gstu.edu.repository.VerificationRepository;
import by.gstu.edu.service.AuthenticateService;
import by.gstu.edu.service.RandomService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final RandomService randomService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(UserRepository userRepository,
                                   VerificationRepository verificationRepository,
                                   RandomService randomService,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.randomService = randomService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TempUser generateTempUser(String email, String code) {
        TempUser user = new TempUser();
        user.setEmail(email);
        user.setLogin(randomService.randomLogin());
        user.setPassword(randomService.randomPasswordUseAll(12));
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setTempUser(user);
        verificationRepository.save(verificationCode);
        return user;
    }

    @Override
    public void confirmVerification(String code) {
        Optional<VerificationCode> verificationCode = verificationRepository.findByCode(code);
        VerificationCode vCode = verificationCode.orElseThrow(() ->
                new VerificationException("Code doesn't exist"));
        if (vCode.getTempUser() != null) {
            userRepository.save(createUserFromTempUser(vCode.getTempUser()));
        }
        verificationRepository.delete(vCode);
    }

    private User createUserFromTempUser(TempUser tempUser) {
        User user = new User();
        user.setEmail(tempUser.getEmail());
        user.setLogin(tempUser.getLogin());
        user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
        user.setConfirmed(true);
        return user;
    }
}
