package by.gstu.courses.controllers.api;

import by.gstu.courses.services.CourseTopicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topics")
public class CourseTopicController {

    private final CourseTopicsService courseTopicsService;

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).CONTROL_LECTURE.name())")
    @GetMapping("/suggest/{name}")
    public List<String> topicSuggestNames(@PathVariable String name) {
        return courseTopicsService.suggest(name);
    }

    // TODO: admin approve topics
}
