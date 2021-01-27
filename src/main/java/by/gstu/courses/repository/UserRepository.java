package by.gstu.courses.repository;

import by.gstu.courses.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    @Query("select u from User u where type(u)=User")
    Page<User> findAllUsersOnly(Pageable pageable);
    @Query("select u from User u where type(u)=User and upper(u.email) like upper(concat('%',:email,'%'))")
    Page<User> findAllUsersOnlyByEmailIgnoreCase(String email, Pageable pageable);
}
