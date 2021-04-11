package by.gstu.courses.services;

import by.gstu.courses.domain.Permissions;
import by.gstu.courses.domain.Role;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface RoleService {
    Iterable<Role> getRoles();
    Iterable<Permissions.Permission> getPermissions();
    Role createRole(Role role);
    Role updateRole(Role role);
    void deleteRoleByName(String name);
}
