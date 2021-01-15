package by.gstu.courses.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<String> permissions = new HashSet<>();

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "role_id"))
    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
