package by.gstu.courses.services.impl;

import by.gstu.courses.domain.CourseTopic;
import by.gstu.courses.exceptions.ResourceItemNotFoundException;
import by.gstu.courses.repository.CourseTopicsRepository;
import by.gstu.courses.services.CourseTopicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service
public class CourseTopicsServiceImpl implements CourseTopicsService {

    private final CourseTopicsRepository courseTopicsRepository;

    @Override
    public List<String> suggest(String name) {
        return courseTopicsRepository.suggestNames(name);
    }

    @Override
    public Page<CourseTopic> getTopicsPage(int page, int size) {
        return courseTopicsRepository.findAll(PageRequest.of(page-1, size));
    }

    @Override
    public CourseTopic createTopic(CourseTopic topic) {
        topic.setApproved(true);
        final CourseTopic courseTopic = setupTopic(topic, new CourseTopic());
        return courseTopicsRepository.save(courseTopic);
    }

    @Transactional
    @Override
    public CourseTopic updateTopic(CourseTopic topic) {
        final CourseTopic courseTopic = courseTopicsRepository.findById(topic.getId())
                .orElseThrow(ResourceItemNotFoundException::new);
        setupTopic(topic, courseTopic);
        return courseTopic;
    }

    @Override
    public Page<CourseTopic> getNotApproved(int page, int size, boolean skipped) {
        return skipped
                ? courseTopicsRepository.findAllByApprovedFalseAndSkippedTrue(PageRequest.of(page-1, size))
                : courseTopicsRepository.findAllByApprovedFalseAndSkippedFalse(PageRequest.of(page-1, size));
    }

    @Override
    public Page<CourseTopic> getApproved(int page, int size) {
        return courseTopicsRepository.findAllByApprovedTrue(PageRequest.of(page-1, size));
    }

    @Transactional
    @Override
    public int deleteTopicById(long id) {
        // TODO: exception
        final CourseTopic topic = courseTopicsRepository.findById(id).orElseThrow(ResourceItemNotFoundException::new);
        final int deleted = courseTopicsRepository.deleteFromIntermediateTableByTopicId(topic.getId());
        courseTopicsRepository.delete(topic);
        return deleted;
    }

    private CourseTopic setupTopic(CourseTopic source, CourseTopic destination) {
        destination.setName(source.getName().toLowerCase());
        destination.setApproved(source.isApproved());
        destination.setSkipped(source.isSkipped());
        return destination;
    }
}
