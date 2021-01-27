package by.gstu.courses.service.impl;

import by.gstu.courses.model.*;
import by.gstu.courses.repository.LecturerRepository;
import by.gstu.courses.repository.RoleRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class UserServiceImpl extends AbstractDefaultService<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LecturerRepository lecturerRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, LecturerRepository lecturerRepository, ModelMapper modelMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.lecturerRepository = lecturerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Lecturer giveLecturerPermissions(String userEmail) {
        return giveLecturerPermissions(userRepository.findByEmail(userEmail));
    }

    @Override
    public Lecturer giveLecturerPermissions(Long id) {
        return giveLecturerPermissions(userRepository.findById(id));
    }

    @Transactional
    @Override
    public User changeRole(Long userId, String newRoleName) {
        final Optional<Lecturer> optionalLecturer = lecturerRepository.findById(userId);
        User user = null;
        Role role = null;

        if (optionalLecturer.isPresent()) {
            role = roleRepository.findById(newRoleName).orElseThrow(/*TODO*/);
            final Set<Permissions.Permission> permissions = role.getPermissions();
            user = optionalLecturer.filter(lec -> permissions.containsAll(lec.getRole().getPermissions()))
                    .orElseThrow(/*TODO*/);
        }

        if (role == null) {
            role = roleRepository.findById(newRoleName).orElseThrow(/*TODO*/);
        }

        if (role.equals(PermanentRoles.LECTURER.entity())) {
            return giveLecturerPermissions(userId);
        }

        if (user == null) {
            user = userRepository.findById(userId).orElseThrow(/*TODO*/);
        }

        user.setRole(role);

        return user;
    }

    @Override
    public List<User> getUsersList(int page, int limit) {
        return getListOfEntities(page, limit, userRepository::findAllUsersOnly);
    }

    @Override
    public List<Lecturer> getLecturerList(int page, int limit) {
        return getListOfEntities(page, limit, lecturerRepository::findAll);
    }

    @Override
    public Page<User> getUsersPage(Pageable pageable, String email) {
        return email == null ? userRepository.findAllUsersOnly(pageable) :
                userRepository.findAllUsersOnlyByEmailIgnoreCase(email, pageable);
    }

    @Override
    public Page<Lecturer> getLecturePage(Pageable pageable, String email) {
        return email == null ? lecturerRepository.findAll(pageable) :
                lecturerRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    @Override
    public Page<User> getPageByEmail(Pageable pageable, String email) {
        return email == null ? userRepository.findAll(pageable) :
                userRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    public Lecturer giveLecturerPermissions(Optional<User> userOptional) {
        return userOptional
                .map(user -> modelMapper.map(user, Lecturer.class))
                .map(lecturerRepository::save)
                .orElseThrow(/* TODO: */);
    }
}
