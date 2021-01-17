package by.gstu.courses.service.impl;

import by.gstu.courses.model.Lecturer;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Lecturer giveLecturerPermissions(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> modelMapper.map(user, Lecturer.class))
                .map(userRepository::save)
                .orElseThrow();
    }
}
