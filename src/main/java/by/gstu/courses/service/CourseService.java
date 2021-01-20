package by.gstu.courses.service;

import by.gstu.courses.model.Course;

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
}
