package by.gstu.courses.repository;

import by.gstu.courses.model.VerificationCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface VerificationRepository extends CrudRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByCode(String code);
}
