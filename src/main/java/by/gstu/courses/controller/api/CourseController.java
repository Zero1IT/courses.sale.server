package by.gstu.courses.controller.api;

import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.exception.DataValidationException;
import by.gstu.courses.model.Course;
import by.gstu.courses.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{page}")
    public List<CourseDto> getCourses(@PathVariable int page,
                                      @RequestParam(name = "limit", defaultValue = "0") int limit) {

        return courseService.getList(page, Limits.pageLimit(limit)).stream()
                .map(it -> modelMapper.map(it, CourseDto.class))
                .collect(Collectors.toList());
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
