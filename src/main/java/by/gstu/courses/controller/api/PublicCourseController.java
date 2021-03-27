package by.gstu.courses.controller.api;

import by.gstu.courses.dto.CourseDto;
import by.gstu.courses.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * createdAt: 3/21/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/course")
public class PublicCourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public PublicCourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public CourseDto getCourse(@PathVariable long id) {
        return courseService.findCourse(id)
                .map(course -> modelMapper.map(course, CourseDto.class))
                .orElse(null);
    }

    @GetMapping("page/{page}")
    public Page<CourseDto> getCourses(@PathVariable int page,
                                      @RequestParam(required = false, defaultValue = "12") int limit) {
        return courseService.getPage(PageRequest.of(page - 1, Limits.pageLimit(limit)))
                .map(course -> modelMapper.map(course, CourseDto.class));
    }
}
