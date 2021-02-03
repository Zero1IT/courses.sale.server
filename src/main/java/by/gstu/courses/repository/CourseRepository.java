package by.gstu.courses.repository;

import by.gstu.courses.model.Course;
import by.gstu.courses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByIdAndLecturer(Long id, User lecturer);
}
