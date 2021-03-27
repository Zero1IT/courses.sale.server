package by.gstu.courses.repository;

import by.gstu.courses.model.Role;
import by.gstu.courses.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select case when count(u.id) > 0 then true else (case when (select count(tu.id) from TempUser tu where tu.email=:email) > 0 then true else false end) end FROM User u where u.email=:email")
    boolean existsAnywhereByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndLecturerInfoNotNull(String email);
    Optional<User> findByIdAndLecturerInfoNotNull(Long id);
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCaseAndLecturerInfoNotNull(String email, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCaseAndLecturerInfoIsNull(String email, Pageable pageable);
    Page<User> findByLecturerInfoIsNull(Pageable pageable);
    Page<User> findByLecturerInfoNotNull(Pageable pageable);
    List<User> findByRole(Role role);
}
