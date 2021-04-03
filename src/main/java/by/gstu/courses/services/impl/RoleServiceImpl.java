package by.gstu.courses.services.impl;

import by.gstu.courses.domain.Role;
import by.gstu.courses.repository.RoleRepository;
import by.gstu.courses.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }
}
