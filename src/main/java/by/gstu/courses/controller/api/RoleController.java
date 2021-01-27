package by.gstu.courses.controller.api;

import by.gstu.courses.model.Role;
import by.gstu.courses.service.RoleService;
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
@RestController
@RequestMapping("/api/admin/roles")
@PreAuthorize("hasRole(T(by.gstu.courses.model.PermanentRoles).ADMIN.getWithPrefix())")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Iterable<Role> getAllRoles() {
        return roleService.getRoles();
    }
}
