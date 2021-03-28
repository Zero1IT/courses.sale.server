package by.gstu.courses.controller.api;

import by.gstu.courses.controller.Utils;
import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.dto.response.EnrollResponse;
import by.gstu.courses.exception.DataValidationException;
import by.gstu.courses.model.Course;
import by.gstu.courses.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/course")
@PreAuthorize("hasAuthority(T(by.gstu.courses.model.Permissions).SIGN_UP_LECTURE.name())")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("enroll/{courseId}")
    public EnrollResponse enrollToCourse(@PathVariable long courseId) {
        final long userId = Utils.getCurrentUserId();
        courseService.enroll(userId, courseId);
        return new EnrollResponse(userId, courseId, true);
    }

    @GetMapping("enroll/{courseId}")
    public EnrollResponse checkEnrolled(@PathVariable long courseId) {
        final long userId = Utils.getCurrentUserId();
        return new EnrollResponse(userId, courseId, courseService.isEnrolled(userId, courseId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("enroll/{courseId}")
    public void unenrollUser(@PathVariable long courseId) {
        final long userId = Utils.getCurrentUserId();
        courseService.unenroll(userId, courseId); // TODO: if false - not found
    }

    @GetMapping("/enrolled/{page}")
    public Page<CourseDto> enrolledCourses(@PathVariable int page) {
        final long userId = Utils.getCurrentUserId();
        final Page<Course> courses = courseService.getUserEnrolledCourses(userId, PageRequest.of(page-1, 10));
        return courses.map(it -> modelMapper.map(it, CourseDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.model.Permissions).CONTROL_LECTURE.name())")
    @GetMapping("/owned/{page}")
    public Page<CourseDto> ownedCourses(@PathVariable int page) {
        final long userId = Utils.getCurrentUserId();
        final Page<Course> courses = courseService.getCoursesByOwner(userId, PageRequest.of(page-1, 10));
        return courses.map(it -> modelMapper.map(it, CourseDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.model.Permissions).CONTROL_LECTURE.name())")
    @PostMapping
    public CourseDto createCourse(@RequestBody @Valid CourseDto courseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult);
        }

        final String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        final Course course = courseService.createCourse(modelMapper.map(courseDto, Course.class), email);
        return course != null ? modelMapper.map(course, CourseDto.class) : null;
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.model.Permissions).CONTROL_LECTURE.name())")
    @PutMapping
    public CourseDto updateCourse(@RequestBody CourseDto courseDto) {
        if (courseDto.getId() == null) {
            throw new DataValidationException("[id] must not be null");
        }

        final Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();
        final Course course = courseService.updateCourse(modelMapper.map(courseDto, Course.class), id);
        return course != null ? modelMapper.map(course, CourseDto.class) : null;
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.model.Permissions).CONTROL_LECTURE.name())")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable long id) {
        final Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getDetails();
        courseService.deleteCourse(id, userId);
    }
}
