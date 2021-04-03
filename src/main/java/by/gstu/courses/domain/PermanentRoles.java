package by.gstu.courses.domain;

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
    ADMIN, LECTURER(false), USER, DEFAULT;

    private final boolean programmatically;

    PermanentRoles(boolean programmatically) {
        this.programmatically = programmatically;
    }

    PermanentRoles() {
        this(true);
    }

    public String getWithPrefix() {
        return "ROLE_" + name();
    }

    public Role entity() {
        Role role = new Role();
        role.setName(name());
        role.setProgrammatically(programmatically);
        role.setChangeable(false);
        return role;
    }
}
