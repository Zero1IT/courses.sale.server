package by.gstu.courses.repository;

import by.gstu.courses.model.Token;
import by.gstu.courses.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * createdAt: 11/25/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByUser(User user);
}
