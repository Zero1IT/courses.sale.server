package by.gstu.edu.repository;

import by.gstu.edu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserRepository extends JpaRepository<User, String> {
}
