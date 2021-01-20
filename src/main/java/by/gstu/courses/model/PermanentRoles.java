package by.gstu.courses.model;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public enum PermanentRoles {
    /**
     *  Database should contains this names as id for `roles`
     */
    ADMIN, LECTURER, USER, DEFAULT;

    public String getWithPrefix() {
        return "ROLE_" + name();
    }

    public Role entity() {
        Role role = new Role();
        role.setName(name());
        return role;
    }
}
