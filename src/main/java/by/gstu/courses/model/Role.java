package by.gstu.courses.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
@Entity
@Table(name = "roles")
public class Role {
    private String name;
    private boolean programmatically;
    private boolean changeable;

    private Set<Permissions.Permission> permissions = new HashSet<>();

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProgrammatically() {
        return programmatically;
    }

    public void setProgrammatically(boolean programmatically) {
        this.programmatically = programmatically;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "role"))
    public Set<Permissions.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions.Permission> permissions) {
        this.permissions = permissions;
    }

    @Transient
    public String getWithPrefix() {
        return "ROLE_" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
