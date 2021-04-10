package by.gstu.courses.services.impl;

import by.gstu.courses.Limits;
import by.gstu.courses.exceptions.ResourceItemNotFoundException;
import by.gstu.courses.domain.Course;
import by.gstu.courses.domain.CourseTopic;
import by.gstu.courses.domain.User;
import by.gstu.courses.exceptions.NotFoundException;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.CourseTopicsRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.services.MutableCourseService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MutableCourseServiceImpl implements MutableCourseService {

    private final CourseTopicsRepository courseTopicsRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Value("${app.course.image.default}")
    private String defaultCourseImagePath;

    @Override
    public Course createCourse(Course course, long userId) {
        return createCourse(course, getLecturer(userId));
    }

    @Override
    public Course updateCourse(Course course, long userId) {
        return updateCourse(course, getLecturer(userId));
    }

    @Override
    public void deleteCourse(long id, long userId) {
        deleteCourse(id, getLecturer(userId));
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
            throw new RuntimeException(); // TODO: you know what you should to do
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
        final Set<CourseTopic> usersCourseTopics = course.getTopics();

        if (usersCourseTopics != null) {
            if (usersCourseTopics.size() > Limits.MAX_TOPICS_PER_COURSE) {
                throw new IllegalArgumentException("too much topics"); // TODO: translate
            }

            usersCourseTopics.forEach(it -> it.setName(it.getName().toLowerCase())); // to db only lc
            final HashSet<CourseTopic> courseTopics = new HashSet<>(usersCourseTopics);
            // TODO: optimize
            final List<String> topics = courseTopics.stream()
                    .map(CourseTopic::getName)
                    .collect(Collectors.toList());
            final Set<CourseTopic> persistedTopics = courseTopicsRepository.findByNameIn(topics);
            persistedTopics.addAll(courseTopics);
            course.setTopics(persistedTopics);
        }

        if (course.getImgUrl() == null) {
            course.setImgUrl(defaultCourseImagePath);
        }
        course.setId(null);
        course.setClosed(false);
        course.setEnded(false);
        course.setLecturer(lecturer);
        course.setCreatedBy(lecturer); // TODO: allow any cases

        return courseRepository.save(course);
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