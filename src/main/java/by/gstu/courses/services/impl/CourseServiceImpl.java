package by.gstu.courses.services.impl;

import by.gstu.courses.controllers.response.ResourceItemNotFoundException;
import by.gstu.courses.domain.Course;
import by.gstu.courses.domain.User;
import by.gstu.courses.repository.CourseRepository;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Course> findCourse(long id) {
        return courseRepository.findById(id);
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

    @Override
    public Page<Course> getUserEnrolledCourses(long userId, Pageable pageable) {
        return courseRepository.findByUsersId(userId, pageable);
    }

    @Override
    public Page<Course> getActualCourses(PageRequest pageRequest) {
        return courseRepository.getActualCourses(pageRequest);
    }
}
