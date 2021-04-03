package by.gstu.courses.services;

import by.gstu.courses.domain.Role;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface RoleService {
    Iterable<Role> getRoles();
}
