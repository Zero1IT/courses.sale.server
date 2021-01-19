package by.gstu.courses.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
public enum Permissions {
    ALL,
    CONTROL_LECTURE,
    SIGN_UP_LECTURE;

    public Permission entity() {
        return new Permission(name());
    }

    @Embeddable
    public static class Permission implements GrantedAuthority {
        private String name;

        public Permission() {}

        public Permission(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // for spring security
        @Transient
        @Override
        public String getAuthority() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Permission that = (Permission) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
