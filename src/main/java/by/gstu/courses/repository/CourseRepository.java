package by.gstu.courses.repository;

import by.gstu.courses.domain.Course;
import by.gstu.courses.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Course> findByIdAndLecturer(Long id, User lecturer);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    @Query("select c from Course c where c.id=:id")
    Optional<Course> lockFindById(Long id);
    Page<Course> findByLecturer(User lecturer, Pageable pageable);
    Page<Course> findByUsersId(Long userId, Pageable pageable);
}
