package by.gstu.courses.service.impl;

import by.gstu.courses.model.Lecturer;
import by.gstu.courses.model.User;
import by.gstu.courses.repository.LecturerRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class UserServiceImpl extends AbstractDefaultService<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final LecturerRepository lecturerRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, LecturerRepository lecturerRepository, ModelMapper modelMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.lecturerRepository = lecturerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Lecturer giveLecturerPermissions(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> modelMapper.map(user, Lecturer.class))
                .map(userRepository::save)
                .orElseThrow();
    }

    @Override
    public List<User> getUsersList(int page, int limit) {
        return getListOfEntities(page, limit, userRepository::findAllUsersOnly);
    }

    @Override
    public List<Lecturer> getLecturerList(int page, int limit) {
        return getListOfEntities(page, limit, lecturerRepository::findAll);
    }
}
