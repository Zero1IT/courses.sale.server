package by.gstu.courses.service.impl;

import by.gstu.courses.model.Role;
import by.gstu.courses.repository.RoleRepository;
import by.gstu.courses.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }
}
