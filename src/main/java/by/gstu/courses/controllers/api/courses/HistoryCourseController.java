package by.gstu.courses.controllers.api.courses;

import by.gstu.courses.controllers.Utils;
import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.domain.Course;
import by.gstu.courses.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course/history")
public class HistoryCourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @GetMapping("/enrolled/{page}")
    public Page<CourseDto> enrolledCourses(@PathVariable int page) {
        final long userId = Utils.getCurrentUserId();
        final Page<Course> courses = courseService.getUserEnrolledCourses(userId, PageRequest.of(page-1, 10));
        return courses.map(it -> modelMapper.map(it, CourseDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @GetMapping("/owned/{page}")
    public Page<CourseDto> ownedCourses(@PathVariable int page) {
        final long userId = Utils.getCurrentUserId();
        final Page<Course> courses = courseService.getCoursesByOwner(userId, PageRequest.of(page-1, 10));
        return courses.map(it -> modelMapper.map(it, CourseDto.class));
    }
}
