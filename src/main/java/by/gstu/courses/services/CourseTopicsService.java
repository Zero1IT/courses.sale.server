package by.gstu.courses.services;

import by.gstu.courses.domain.CourseTopic;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseTopicsService {
    List<String> suggest(String name);
    CourseTopic createTopic(CourseTopic map);
    CourseTopic updateTopic(CourseTopic topic);
    Page<CourseTopic> getTopicsPage(int page, int size);
    Page<CourseTopic> getNotApproved(int page, int size, boolean skipped);
    Page<CourseTopic> getApproved(int page, int size);
    int deleteTopicById(long id);
}
