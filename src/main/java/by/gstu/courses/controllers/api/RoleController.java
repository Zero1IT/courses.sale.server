package by.gstu.courses.controllers.api;

import by.gstu.courses.domain.Permissions;
import by.gstu.courses.domain.Role;
import by.gstu.courses.dto.RoleDto;
import by.gstu.courses.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/perms")
    public Iterable<Permissions.Permission> getAllPermissions() {
        return roleService.getPermissions();
    }

    @PostMapping
    public Role createRole(@RequestBody @Valid RoleDto roleDto) {
        return roleService.createRole(getRole(roleDto));
    }

    @PutMapping
    public Role updateRole(@RequestBody @Valid RoleDto roleDto) {
        return roleService.updateRole(getRole(roleDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{name}")
    public Map<String, Integer> deleteRole(@PathVariable String name, @RequestParam(name = "to") String toRole) {
        Integer recordsChanges = roleService.deleteRoleByName(name, toRole);
        Map<String, Integer> map = new HashMap<>();
        map.put("updated", recordsChanges);
        return map;
    }

    @NotNull
    private Role getRole(RoleDto roleDto) {
        final Role role = new Role();
        final Set<String> permissions = roleDto.getPermissions();
        if (permissions != null) {
            role.setPermissions(permissions.stream()
                    .map(Permissions.Permission::new)
                    .collect(Collectors.toSet()));
        } else {
            role.setPermissions(Collections.emptySet());
        }
        final String name = roleDto.getName();
        if (name != null) {
            role.setName(name.toUpperCase());
        }
        return role;
    }
}
