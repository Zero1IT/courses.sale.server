package by.gstu.courses;

import by.gstu.courses.model.PermanentRoles;
import by.gstu.courses.model.Permissions;
import by.gstu.courses.model.Role;
import by.gstu.courses.repository.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
@Component
public class RoleStartInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public RoleStartInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        final Map<Role, Consumer<Set<Permissions.Permission>>> populators = new HashMap<>();
        populators.put(PermanentRoles.ADMIN.entity(), this::populateAdminPermissions);
        populators.put(PermanentRoles.LECTURER.entity(), this::populateLecturerPermissions);
        populators.put(PermanentRoles.USER.entity(), this::populateUserPermissions);
        populators.put(PermanentRoles.DEFAULT.entity(), this::populateDefaultPermissions);
        initializeRoleTable(populators);
    }

    private void initializeRoleTable(Map<Role, Consumer<Set<Permissions.Permission>>> populators) {
        final List<Role> list = StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        final boolean allMatch = !list.isEmpty() && list.stream().allMatch(populators::containsKey);

        if (!allMatch) {
            final Set<Role> roles = populators.entrySet().stream()
                    .map(e -> {
                        final Role role = e.getKey();
                        e.getValue().accept(role.getPermissions());
                        return role;
                    })
                    .collect(Collectors.toSet());

            roleRepository.saveAll(roles);
        }
    }

    private void populateAdminPermissions(Set<Permissions.Permission> permissions) {
        populateLecturerPermissions(permissions);
        permissions.add(Permissions.ALL.entity());
    }

    private void populateLecturerPermissions(Set<Permissions.Permission> permissions) {
        populateUserPermissions(permissions);
        permissions.add(Permissions.CONTROL_LECTURE.entity());
    }

    private void populateUserPermissions(Set<Permissions.Permission> permissions) {
        populateDefaultPermissions(permissions);
        permissions.add(Permissions.SIGN_UP_LECTURE.entity());
    }

    private void populateDefaultPermissions(Set<Permissions.Permission> permissions) {
        // no perms
    }
}
