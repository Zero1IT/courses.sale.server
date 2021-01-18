package by.gstu.courses.repository;

import by.gstu.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}
