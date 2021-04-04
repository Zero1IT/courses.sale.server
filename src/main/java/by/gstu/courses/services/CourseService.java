package by.gstu.courses.services;

import by.gstu.courses.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseService {
    boolean isEnrolled(Long userId, Long courseId);

    Optional<Course> findCourse(long id);
    Page<Course> getCoursesByOwner(long ownerId, Pageable pageable);
    Page<Course> getUserEnrolledCourses(long userId, Pageable pageable);
    Page<Course> getActualCourses(PageRequest pageRequest);
    Page<Course> getUsersInCourse(long ownerId, long courseId, Pageable pageable);
}
