package by.gstu.courses.controllers.api;

import by.gstu.courses.domain.CourseTopic;
import by.gstu.courses.dto.CourseTopicDto;
import by.gstu.courses.services.CourseTopicsService;
import by.gstu.courses.validation.group.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private final ModelMapper mapper;

    @GetMapping("/suggest/{name}")
    public List<String> topicSuggestNames(@PathVariable String name) {
        return courseTopicsService.suggest(name);
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @GetMapping("{page}")
    public Page<CourseTopicDto> getTopics(@PathVariable int page) {
        return courseTopicsService.getTopicsPage(page, 20)
                .map(it -> mapper.map(it, CourseTopicDto.class)); // TODO: think about nums
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @GetMapping("/approved/{page}")
    public Page<CourseTopicDto> getApprovedTopics(@PathVariable int page) {
        return courseTopicsService.getApproved(page, 20)
                .map(it -> mapper.map(it, CourseTopicDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @GetMapping("/new/{page}")
    public Page<CourseTopicDto> getNotApprovedTopics(@PathVariable int page) {
        return courseTopicsService.getNotApproved(page, 20, false)
                .map(it -> mapper.map(it, CourseTopicDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @GetMapping("/skipped/{page}")
    public Page<CourseTopicDto> getSkippedTopics(@PathVariable int page) {
        return courseTopicsService.getNotApproved(page, 20, true)
                .map(it -> mapper.map(it, CourseTopicDto.class));
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @PostMapping
    public CourseTopicDto createTopic(@RequestBody @Valid CourseTopicDto dto) {
        return mapper.map(courseTopicsService.createTopic(mapper.map(dto, CourseTopic.class)), CourseTopicDto.class);
    }

    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @PutMapping
    public CourseTopicDto updateTopic(@RequestBody @Validated(UpdateGroup.class) CourseTopicDto dto) {
        return mapper.map(courseTopicsService.updateTopic(mapper.map(dto, CourseTopic.class)), CourseTopicDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).TOPIC_CONTROL.name())")
    @DeleteMapping("{id}")
    public void deleteTopic(@PathVariable long id) {
        courseTopicsService.deleteTopicById(id);
    }
}
