package by.gstu.courses.service.impl;

import by.gstu.courses.exception.UserExistsException;
import by.gstu.courses.exception.VerificationException;
import by.gstu.courses.model.Role;
import by.gstu.courses.model.TempUser;
import by.gstu.courses.model.User;
import by.gstu.courses.model.VerificationCode;
import by.gstu.courses.repository.TempUserRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.repository.VerificationRepository;
import by.gstu.courses.service.AuthenticateService;
import by.gstu.courses.service.RandomService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final TempUserRepository tempUserRepository;

    public AuthenticateServiceImpl(UserRepository userRepository,
                                   VerificationRepository verificationRepository,
                                   RandomService randomService,
                                   PasswordEncoder passwordEncoder,
                                   TempUserRepository tempUserRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.randomService = randomService;
        this.passwordEncoder = passwordEncoder;
        this.tempUserRepository = tempUserRepository;
    }

    @Override
    public TempUser generateTempUser(String email) {
        if (userRepository.existsAnywhereByEmail(email)) {
            throw new UserExistsException("User with current email already exists");
        }
        TempUser user = new TempUser();
        user.setEmail(email);
        user.setLogin(randomService.randomLogin());
        user.setPassword(randomService.randomPasswordUseAll(12));
        tempUserRepository.save(user);

        return user;
    }

    @Override
    public User transferTempUser(String email) {
        final TempUser tempUser = tempUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        final User user = userRepository.save(createUserFromTempUser(tempUser));
        tempUserRepository.delete(tempUser);
        return user;
    }

    @Override
    public User registrationUser(User user, String code) {
        if (userRepository.existsAnywhereByEmail(user.getEmail())) {
            throw new UserExistsException("User with current email already exists");
        }

        user.setRole(Role.DEFAULT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmed(false);

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setUser(user);

        return verificationRepository.save(verificationCode).getUser();
    }

    @Override
    public void confirmVerification(String code) {
        final VerificationCode vCode = verificationRepository.findByCode(code).orElseThrow(() ->
                new VerificationException("Code doesn't exist"));
        final User user = vCode.getUser();

        if (user != null) {
            user.setConfirmed(true);
            userRepository.save(user);
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
