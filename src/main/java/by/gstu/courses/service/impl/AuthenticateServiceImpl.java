package by.gstu.courses.service.impl;

import by.gstu.courses.exception.UserExistsException;
import by.gstu.courses.exception.VerificationException;
import by.gstu.courses.model.PermanentRoles;
import by.gstu.courses.model.TempUser;
import by.gstu.courses.model.User;
import by.gstu.courses.model.VerificationCode;
import by.gstu.courses.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    public AuthenticateServiceImpl(UserRepository userRepository,
                                   VerificationRepository verificationRepository,
                                   RandomService randomService,
                                   PasswordEncoder passwordEncoder,
                                   TempUserRepository tempUserRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.randomService = randomService;
        this.passwordEncoder = passwordEncoder;
        this.tempUserRepository = tempUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public TempUser generateTempUser(String email, String code) {
        if (userRepository.existsAnywhereByEmail(email)) {
            throw new UserExistsException("User with current email already exists");
        }

        TempUser user = new TempUser();
        user.setEmail(email);
        user.setLogin(randomService.randomLogin());
        user.setActivationUUID(code);

        String password = randomService.randomPasswordUseAll(12);
        user.setPassword(passwordEncoder.encode(password));
        user = tempUserRepository.save(user);

        user.setPassword(password); // use raw password for send to email
        return user;
    }

    @Override
    public User transferTempUser(String code) {
        final TempUser tempUser = tempUserRepository.findByActivationUUID(code)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        final User user = userRepository.save(createUserFromTempUser(tempUser));
        tempUserRepository.delete(tempUser);
        return user;
    }

    @Override
    public User registrationUser(User user, String code) {
        if (userRepository.existsAnywhereByEmail(user.getEmail())) {
            throw new UserExistsException("User with current email already exists");
        }

        roleRepository.findById(PermanentRoles.DEFAULT.name())
                .ifPresent(user::setRole);
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
            confirmedUser(user);
            userRepository.save(user);
        }

        verificationRepository.delete(vCode);
    }

    private void confirmedUser(User user) {
        roleRepository
                .findById(PermanentRoles.USER.name())
                .ifPresent(user::setRole);
        user.setConfirmed(true);
    }

    private User createUserFromTempUser(TempUser tempUser) {
        User user = new User();
        user.setEmail(tempUser.getEmail());
        user.setLogin(tempUser.getLogin());
        user.setPassword(tempUser.getPassword());
        confirmedUser(user);
        return user;
    }
}
