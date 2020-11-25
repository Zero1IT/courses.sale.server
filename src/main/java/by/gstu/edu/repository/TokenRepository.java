package by.gstu.edu.repository;

import by.gstu.edu.model.Token;
import by.gstu.edu.model.User;
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
