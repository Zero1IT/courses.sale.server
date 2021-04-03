package by.gstu.courses.services;

import by.gstu.courses.domain.Course;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface MutableCourseService {
    Course createCourse(Course course, long userId);
    Course updateCourse(Course course, long userId);
    void deleteCourse(long id, long userId);

    void enroll(Long userId, Long courseId);
    boolean unenroll(Long userId, Long courseId);
}
