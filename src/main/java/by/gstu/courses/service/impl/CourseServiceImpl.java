package by.gstu.courses.service.impl;

import by.gstu.courses.model.Course;
import by.gstu.courses.model.Lecturer;
import by.gstu.courses.model.PermanentRoles;
import by.gstu.courses.model.Role;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.CourseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(Course course, String email) {
        course.setClosed(false);
        course.setEnded(false);
        course.setLecturer(userRepository.getLecturer(email));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getNewCourse(int limit, int page) {
        if (limit > 20) {
            throw new IllegalArgumentException("Limit is too much");
        }
        return courseRepository.findAll(PageRequest.of(page, limit, Sort.Direction.DESC)).getContent();
    }
}