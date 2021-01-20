package by.gstu.courses.service.impl;

import by.gstu.courses.exception.NotFoundException;
import by.gstu.courses.model.Course;
import by.gstu.courses.model.Lecturer;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
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
    public List<Course> getNewCourse(int page, int limit) {
        if (limit > 20 || limit < 0) {
            throw new IllegalArgumentException("Limit is too much");
        }

        if (limit == 0) {
            return Collections.emptyList();
        }
        return courseRepository.findAll(PageRequest.of(page, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }

    @Override
    public Course createCourse(Course course, long userId) {
        return createCourse(course, userRepository.getLecturer(userId));
    }

    @Override
    public Course createCourse(Course course, String email) {
        return createCourse(course, userRepository.getLecturer(email));
    }

    @NotNull
    private Course createCourse(Course course, Lecturer lecturer) {
        course.setId(null);
        course.setClosed(false);
        course.setEnded(false);
        course.setLecturer(lecturer);
        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, long userId) {
        return updateCourse(course, userRepository.getLecturer(userId));
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, String email) {
        return updateCourse(course, userRepository.getLecturer(email));
    }

    @NotNull
    private Course updateCourse(Course course, Lecturer lecturer) {
        final Course updatedCourse = courseRepository
                .findByIdAndLecturer(course.getId(), lecturer)
                .orElseThrow(NotFoundException::new);

        if (course.getTitle() != null)
            updatedCourse.setTitle(course.getTitle());
        if (course.getDescription() != null)
            updatedCourse.setDescription(course.getDescription());
        if (course.getDeferredPaymentDays() != null)
            updatedCourse.setDeferredPaymentDays(course.getDeferredPaymentDays());
        if (!updatedCourse.isClosed() && course.getStartDate() != null)
            updatedCourse.setStartDate(course.getStartDate());

        return updatedCourse;
    }

    @Transactional
    @Override
    public void deleteCourse(long id, long userId) {
        deleteCourse(id, userRepository.getLecturer(userId));
    }

    @Transactional
    @Override
    public void deleteCourse(long id, String email) {
        deleteCourse(id, userRepository.getLecturer(email));
    }

    private void deleteCourse(long id, Lecturer lecturer) {
        final Course course = courseRepository.findByIdAndLecturer(id, lecturer)
                .orElseThrow(NotFoundException::new);
        courseRepository.delete(course);
    }
}