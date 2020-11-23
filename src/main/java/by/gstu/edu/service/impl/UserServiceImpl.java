package by.gstu.edu.service.impl;

import by.gstu.edu.model.TempUser;
import by.gstu.edu.model.VerificationCode;
import by.gstu.edu.repository.VerificationRepository;
import by.gstu.edu.service.RandomService;
import by.gstu.edu.service.UserService;
import org.springframework.stereotype.Service;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class UserServiceImpl implements UserService {

    private final VerificationRepository verificationRepository;
    private final RandomService randomService;

    public UserServiceImpl(VerificationRepository verificationRepository, RandomService randomService) {
        this.verificationRepository = verificationRepository;
        this.randomService = randomService;
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
}
