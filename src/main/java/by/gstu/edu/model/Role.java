package by.gstu.edu.model;

import java.util.Set;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public enum Role {
    ADMIN(Set.of(Permission.values())),
    MODERATOR(Set.of(Permission.values())),
    LECTURER(Set.of(Permission.U_LECTURE_W)),
    DEFAULT(Set.of());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
