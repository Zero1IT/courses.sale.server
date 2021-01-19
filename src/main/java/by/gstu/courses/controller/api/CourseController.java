package by.gstu.courses.controller.api;

import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.exception.DataValidationException;
import by.gstu.courses.model.Course;
import by.gstu.courses.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
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
}
