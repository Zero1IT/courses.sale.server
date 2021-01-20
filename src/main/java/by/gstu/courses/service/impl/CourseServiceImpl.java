package by.gstu.courses.service.impl;

import by.gstu.courses.exception.NotFoundException;
import by.gstu.courses.model.Course;
import by.gstu.courses.model.Lecturer;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.LecturerRepository;
import by.gstu.courses.service.CourseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class CourseServiceImpl extends AbstractDefaultService<Course, Long> implements CourseService {

    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;

    public CourseServiceImpl(CourseRepository courseRepository, LecturerRepository lecturerRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Course createCourse(Course course, long userId) {
        return createCourse(course, lecturerRepository.getOne(userId));
    }

    @Override
    public Course createCourse(Course course, String email) {
        return createCourse(course, lecturerRepository.getOne(email));
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
        return updateCourse(course, lecturerRepository.getOne(userId));
    }

    @Transactional
    @Override
    public Course updateCourse(Course course, String email) {
        return updateCourse(course, lecturerRepository.getOne(email));
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
        deleteCourse(id, lecturerRepository.getOne(userId));
    }

    @Transactional
    @Override
    public void deleteCourse(long id, String email) {
        deleteCourse(id, lecturerRepository.getOne(email));
    }

    private void deleteCourse(long id, Lecturer lecturer) {
        final Course course = courseRepository.findByIdAndLecturer(id, lecturer)
                .orElseThrow(NotFoundException::new);
        courseRepository.delete(course);
    }
}