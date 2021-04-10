package by.gstu.courses.services.impl;

import by.gstu.courses.exceptions.IncompatiblePermissionsException;
import by.gstu.courses.exceptions.ResourceItemNotFoundException;
import by.gstu.courses.domain.*;
import by.gstu.courses.repository.RoleRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User giveLecturerPermissions(String userEmail) {
        return giveLecturerPermissions(userRepository.findByEmail(userEmail));
    }

    @Override
    public User giveLecturerPermissions(Long id) {
        return giveLecturerPermissions(userRepository.findById(id));
    }

    @Transactional
    @Override
    public User changeRole(Long userId, String newRoleName) {
        final Optional<User> optionalLecturer = userRepository.findByIdAndLecturerInfoNotNull(userId);
        User user = null;
        Role role = null;

        if (optionalLecturer.isPresent()) {
            role = roleRepository.findById(newRoleName).orElseThrow(ResourceItemNotFoundException::new);
            final Set<Permissions.Permission> permissions = role.getPermissions();
            user = optionalLecturer.filter(lec -> permissions.containsAll(lec.getRole().getPermissions()))
                    .orElseThrow(IncompatiblePermissionsException::new);
        }

        if (role == null) {
            role = roleRepository.findById(newRoleName).orElseThrow(ResourceItemNotFoundException::new);
        }

        if (role.equals(PermanentRoles.LECTURER.entity())) {
            return giveLecturerPermissions(userId);
        }

        if (user == null) {
            user = userRepository.findById(userId).orElseThrow(ResourceItemNotFoundException::new);
        }

        user.setRole(role);

        return user;
    }

    @Override
    public List<User> getUsersList(int page, int limit) {
        return userRepository.findByLecturerInfoIsNull(PageRequest.of(page-1, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }

    @Override
    public List<User> getLecturerList(int page, int limit) {
        return userRepository.findByLecturerInfoNotNull(PageRequest.of(page-1, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }

    @Override
    public Page<User> getUsersPage(Pageable pageable, String email) {
        return email == null ? userRepository.findByLecturerInfoIsNull(pageable) :
                userRepository.findByEmailContainingIgnoreCaseAndLecturerInfoIsNull(email, pageable);
    }

    @Override
    public Page<User> getLecturePage(Pageable pageable, String email) {
        return email == null ? userRepository.findByLecturerInfoNotNull(pageable) :
                userRepository.findByEmailContainingIgnoreCaseAndLecturerInfoNotNull(email, pageable);
    }

    @Override
    public Page<User> getPageByEmail(Pageable pageable, String email) {
        return email == null ? userRepository.findAll(pageable) :
                userRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    public User giveLecturerPermissions(Optional<User> userOptional) {
        return userOptional.map(user -> {
                    user.setLecturerInfo(new LecturerInfo());
                    return user;
                })
                .map(userRepository::save)
                .orElseThrow();
    }

    @Override
    public List<User> getList(int page, int limit) {
        return userRepository.findAll(PageRequest.of(page-1, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }

    @Override
    public Optional<User> findUserById(long currentUserId) {
        return userRepository.findById(currentUserId);
    }
}
