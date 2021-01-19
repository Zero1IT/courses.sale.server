package by.gstu.courses.service;

import by.gstu.courses.model.Course;

import java.util.List;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseService {
    List<Course> getNewCourse(int limit, int page);
    Course createCourse(Course course, String email);
}
