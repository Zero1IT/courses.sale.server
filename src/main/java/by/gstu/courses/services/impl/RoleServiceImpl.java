package by.gstu.courses.services.impl;

import by.gstu.courses.domain.Permissions;
import by.gstu.courses.domain.Role;
import by.gstu.courses.exceptions.ResourceItemNotFoundException;
import by.gstu.courses.repository.RoleRepository;
import by.gstu.courses.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Override
    public Iterable<Permissions.Permission> getPermissions() {
        return roleRepository.selectAllDistinctPermissions();
    }

    @Override
    public Role createRole(Role role) {
        roleRepository.findById(role.getName()).ifPresent(item -> {
            throw new IllegalStateException(item.getName()); // TODO: change exception
        });

        role.setProgrammatically(false);
        role.setChangeable(true);
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public Role updateRole(Role role) {
        final Role persistedRole = roleRepository.findById(role.getName())
                .orElseThrow(ResourceItemNotFoundException::new);
        if (persistedRole.isChangeable()) {
            if (!role.getPermissions().isEmpty()) {
                persistedRole.setPermissions(role.getPermissions());
            }
            return persistedRole;
        }
        throw new UnsupportedOperationException(); // TODO: valid exception
    }

    @Transactional
    @Override
    public int deleteRoleByName(String name, String toRole) {
        // TODO: shit
        final Role role = roleRepository.findById(name).orElseThrow(ResourceItemNotFoundException::new);
        if (role.isChangeable()) {
            final Role newRole = roleRepository.findById(toRole).orElseThrow(ResourceItemNotFoundException::new);
            if (!newRole.isProgrammatically() && !role.equals(newRole)) {
                final int usersChanged = roleRepository.migrateFromRole(role, newRole);
                roleRepository.delete(role);
                return usersChanged;
            }
        }
        throw new UnsupportedOperationException(); // TODO shit
    }
}
