package by.gstu.courses.repository;

import by.gstu.courses.domain.Course;
import by.gstu.courses.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.Instant;
import java.util.Optional;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Course> findByIdAndLecturer(Long id, User lecturer);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    @Query("select c from Course c where c.id=:id")
    Optional<Course> lockFindById(Long id);

    Page<Course> findByLecturer(User lecturer, Pageable pageable);
    Page<Course> findByUsersId(Long userId, Pageable pageable);
    Page<Course> findAllByClosedFalseAndEndedFalseAndStartDateAfter(Instant startDate, Pageable pageable);

    default Page<Course> getActualCourses(Pageable pageable) {
        return findAllByClosedFalseAndEndedFalseAndStartDateAfter(Instant.now(), pageable);
    }
}
