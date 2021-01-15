package by.gstu.courses;

import by.gstu.courses.model.PermanentRoles;
import by.gstu.courses.model.Permissions;
import by.gstu.courses.model.Role;
import by.gstu.courses.repository.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
@Component
public class RoleStartInitializer implements ApplicationRunner {

    private final Map<String, Consumer<Set<String>>> populators = new HashMap<>();

    {
        populators.put(PermanentRoles.ADMIN.name(), this::populateAdminPermissions);
        populators.put(PermanentRoles.LECTURER.name(), this::populateLecturerPermissions);
        populators.put(PermanentRoles.USER.name(), this::populateUserPermissions);
        populators.put(PermanentRoles.DEFAULT.name(), this::populateDefaultPermissions);
    }

    private final RoleRepository roleRepository;

    public RoleStartInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final Set<Role> roles = populators.entrySet().stream()
                .map(e -> {
                    final Role role = new Role();
                    role.setName(e.getKey());
                    e.getValue().accept(role.getPermissions());
                    return role;
                })
                .collect(Collectors.toSet());

        roleRepository.saveAll(roles);
    }

    private void populateAdminPermissions(Set<String> permissions) {
        populateLecturerPermissions(permissions);
        permissions.add(Permissions.ALL.name());
    }

    private void populateLecturerPermissions(Set<String> permissions) {
        populateUserPermissions(permissions);
        permissions.add(Permissions.CONTROL_LECTURE.name());
    }

    private void populateUserPermissions(Set<String> permissions) {
        populateDefaultPermissions(permissions);
        permissions.add(Permissions.SIGN_UP_LECTURE.name());
    }

    private void populateDefaultPermissions(Set<String> permissions) {
        // no perms
    }
}
