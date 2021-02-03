package by.gstu.courses.service.impl;

import by.gstu.courses.exception.NotFoundException;
import by.gstu.courses.model.Course;
import by.gstu.courses.model.User;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Course createCourse(Course course, long userId) {
        return createCourse(course, userRepository.findByIdAndLecturerInfoNotNull(userId).orElseThrow(/*TODO*/));
    }

    @Override
    public Course createCourse(Course course, String email) {
        return createCourse(course, userRepository.findByEmailAndLecturerInfoNotNull(email).orElseThrow(/*TODO*/));
    }

    @NotNull
    private Course createCourse(Course course, User lecturer) {
        course.setId(null);
        course.setClosed(false);
        course.setEnded(false);
        course.setLecturer(lecturer);
        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, long userId) {
        return updateCourse(course, userRepository.findByIdAndLecturerInfoNotNull(userId).orElseThrow(/*TODO*/));
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, String email) {
        return updateCourse(course, userRepository.findByEmailAndLecturerInfoNotNull(email).orElseThrow(/*TODO*/));
    }

    @NotNull
    private Course updateCourse(Course course, User lecturer) {
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
        deleteCourse(id, userRepository.findByIdAndLecturerInfoNotNull(userId).orElseThrow(/*TODO*/));
    }

    @Transactional
    @Override
    public void deleteCourse(long id, String email) {
        deleteCourse(id, userRepository.findByEmailAndLecturerInfoNotNull(email).orElseThrow(/*TODO*/));
    }

    private void deleteCourse(long id, User lecturer) {
        final Course course = courseRepository.findByIdAndLecturer(id, lecturer)
                .orElseThrow(NotFoundException::new);
        courseRepository.delete(course);
    }

    @Override
    public List<Course> getList(int page, int limit) {
        return courseRepository.findAll(PageRequest.of(page-1, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }

    @Override
    public Page<Course> getPage(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}