package by.gstu.courses.model;

import lombok.Data;

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
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String name;
    private boolean programmatically;
    private boolean changeable;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "role"))
    private Set<Permissions.Permission> permissions = new HashSet<>();

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
