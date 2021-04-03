package by.gstu.courses.repository;

import by.gstu.courses.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
public interface RoleRepository extends CrudRepository<Role, String> {
}
