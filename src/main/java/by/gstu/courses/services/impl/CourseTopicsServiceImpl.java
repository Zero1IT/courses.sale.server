package by.gstu.courses.services.impl;

import by.gstu.courses.repository.CourseTopicsRepository;
import by.gstu.courses.services.CourseTopicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
