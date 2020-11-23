package by.gstu.edu.repository;

import by.gstu.edu.model.VerificationCode;
import org.springframework.data.repository.CrudRepository;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface VerificationRepository extends CrudRepository<VerificationCode, Long> {
}
