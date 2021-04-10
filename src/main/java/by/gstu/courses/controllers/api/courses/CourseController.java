package by.gstu.courses.controllers.api.courses;

import by.gstu.courses.controllers.AccountInfo;
import by.gstu.courses.domain.Course;
import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.dto.UserDto;
import by.gstu.courses.dto.response.EnrollResponse;
import by.gstu.courses.services.CourseService;
import by.gstu.courses.services.MutableCourseService;
import by.gstu.courses.validation.group.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course")
@PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).SIGN_UP_LECTURE.name())")
public class CourseController {

    private final MutableCourseService mutableCourseService;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @PostMapping("/enroll/{courseId}")
    public EnrollResponse enrollToCourse(@PathVariable long courseId) {
        final long userId = AccountInfo.getCurrentUserId();
        mutableCourseService.enroll(userId, courseId);
        return new EnrollResponse(userId, courseId, true);
    }

    @GetMapping("/enroll/{courseId}")
    public EnrollResponse checkEnrolled(@PathVariable long courseId) {
        final long userId = AccountInfo.getCurrentUserId();
        return new EnrollResponse(userId, courseId, courseService.isEnrolled(userId, courseId));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/enroll/{courseId}")
    public void unenrollUser(@PathVariable long courseId) {
        final long userId = AccountInfo.getCurrentUserId();
        mutableCourseService.unenroll(userId, courseId); // TODO: if false - not found
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @GetMapping("/enrolled/{courseId}/{page}")
    public Page<UserDto> getEnrolledUsers(@PathVariable long courseId, @PathVariable int page) {
        final long currentUserId = AccountInfo.getCurrentUserId();
        return courseService.getUsersInCourse(currentUserId, courseId,
                PageRequest.of(page-1, 10)
        ).map(it -> modelMapper.map(it, UserDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @PostMapping
    public CourseDto createCourse(@RequestBody @Valid CourseDto courseDto) {
        final long userId = AccountInfo.getCurrentUserId();
        final Course course = mutableCourseService.createCourse(modelMapper.map(courseDto, Course.class), userId);
        return course != null ? modelMapper.map(course, CourseDto.class) : null;
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @PutMapping
    public CourseDto updateCourse(@RequestBody @Validated(UpdateGroup.class) CourseDto courseDto) {
        final long userId = AccountInfo.getCurrentUserId();
        final Course course = mutableCourseService.updateCourse(modelMapper.map(courseDto, Course.class), userId);
        return course != null ? modelMapper.map(course, CourseDto.class) : null;
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable long id) {
        final long userId = AccountInfo.getCurrentUserId();
        mutableCourseService.deleteCourse(id, userId);
    }
}
