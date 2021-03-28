package by.gstu.courses.service;

import by.gstu.courses.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseService extends DefaultService<Course> {
    Course createCourse(Course course, long userId);
    Course createCourse(Course course, String email);
    Course updateCourse(Course course, long userId);
    Course updateCourse(Course course, String email);
    void deleteCourse(long id, long userId);
    void deleteCourse(long id, String email);
    Optional<Course> findCourse(long id);

    void enroll(Long userId, Long courseId);
    boolean unenroll(Long userId, Long courseId);
    boolean isEnrolled(Long userId, Long courseId);
    Page<Course> getCoursesByOwner(long ownerId, Pageable pageable);
    Page<Course> getUserEnrolledCourses(long userId, Pageable pageable);
}
