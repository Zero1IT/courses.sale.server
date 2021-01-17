package by.gstu.courses.repository;

import by.gstu.courses.model.TempUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * createdAt: ${DATE}
 * project: ${PROJECT_NAME}
 *
 * @author Alexander Petrushkin
 */
public interface TempUserRepository extends CrudRepository<TempUser, Long> {
    Optional<TempUser> findByActivationUUID(String activationUUID);
}
