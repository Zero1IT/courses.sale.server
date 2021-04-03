package by.gstu.courses.services.impl;

import by.gstu.courses.controllers.response.ResourceItemNotFoundException;
import by.gstu.courses.exceptions.NotFoundException;
import by.gstu.courses.domain.Course;
import by.gstu.courses.domain.User;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Value("${spring.course.image.default}")
    private String defaultCourseImagePath;

    @Override
    public Optional<Course> findCourse(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course, long userId) {
        return createCourse(course, getLecturer(userId));
    }

    @Override
    public Course createCourse(Course course, String email) {
        return createCourse(course, getLecturer(email));
    }

    @Override
    public Course updateCourse(Course course, long userId) {
        return updateCourse(course, getLecturer(userId));
    }

    @Override
    public Course updateCourse(Course course, String email) {
        return updateCourse(course, getLecturer(email));
    }

    @Override
    public void deleteCourse(long id, long userId) {
        deleteCourse(id, getLecturer(userId));
    }

    @Override
    public void deleteCourse(long id, String email) {
        deleteCourse(id, getLecturer(email));
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

    @Override
    public void enroll(Long userId, Long courseId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(ResourceItemNotFoundException::new);
        final Course course = courseRepository.lockFindById(courseId)
                .orElseThrow(ResourceItemNotFoundException::new);

        final Set<User> subscribers = course.getUsers();
        if (!subscribers.contains(user) && subscribers.size() < course.getPlaces()) {
            subscribers.add(user);
        } else {
            // TODO: you know what you should to do
        }
    }

    @Override
    public boolean unenroll(Long userId, Long courseId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(ResourceItemNotFoundException::new);
        return courseRepository.lockFindById(courseId)
                .orElseThrow(ResourceItemNotFoundException::new)
                .getUsers().remove(user);
    }

    @Override
    public boolean isEnrolled(Long userId, Long courseId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(ResourceItemNotFoundException::new);
        return courseRepository.lockFindById(courseId)
                .orElseThrow(ResourceItemNotFoundException::new)
                .getUsers().contains(user);
    }

    @Override
    public Page<Course> getCoursesByOwner(long ownerId, Pageable pageable) {
        return userRepository.findByIdAndLecturerInfoNotNull(ownerId)
                .map(it -> courseRepository.findByLecturer(it, pageable))
                .orElse(Page.empty());
    }

    @Transactional
    @Override
    public Page<Course> getUserEnrolledCourses(long userId, Pageable pageable) {
        return courseRepository.findByUsersId(userId, pageable);
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
        if (!updatedCourse.isClosed() && course.getStartDate() != null)
            updatedCourse.setStartDate(course.getStartDate());

        return updatedCourse;
    }

    @NotNull
    private Course createCourse(Course course, User lecturer) {
        course.setId(null);
        course.setClosed(false);
        course.setEnded(false);
        course.setLecturer(lecturer);
        if (course.getImgUrl() == null) {
            course.setImgUrl(defaultCourseImagePath);
        }
        course.setCreatedBy(lecturer); // TODO: allow any cases
        return courseRepository.save(course);
    }

    private User getLecturer(String email) {
        return userRepository.findByEmailAndLecturerInfoNotNull(email)
                .orElseThrow(ResourceItemNotFoundException::new);
    }

    private User getLecturer(long userId) {
        return userRepository.findByIdAndLecturerInfoNotNull(userId)
                .orElseThrow(ResourceItemNotFoundException::new);
    }

    private void deleteCourse(long id, User lecturer) {
        final Course course = courseRepository.findByIdAndLecturer(id, lecturer)
                .orElseThrow(NotFoundException::new);
        courseRepository.delete(course);
    }
}