package by.gstu.courses.controllers.api;

import by.gstu.courses.domain.Role;
import by.gstu.courses.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * createdAt: 1/23/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/roles")
@PreAuthorize("hasRole(T(by.gstu.courses.domain.PermanentRoles).ADMIN.getWithPrefix())")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public Iterable<Role> getAllRoles() {
        return roleService.getRoles();
    }
}
