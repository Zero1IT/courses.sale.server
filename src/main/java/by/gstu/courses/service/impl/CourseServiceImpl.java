package by.gstu.courses.service.impl;

import by.gstu.courses.model.Course;
import by.gstu.courses.repository.CourseRepository;
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

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getNewCourse(int limit, int page) {
        if (limit > 20) {
            throw new IllegalArgumentException("Limit is too much");
        }
        return courseRepository.findAll(PageRequest.of(page, limit, Sort.Direction.DESC)).getContent();
    }
}