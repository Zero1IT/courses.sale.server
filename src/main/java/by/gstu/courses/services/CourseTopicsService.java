package by.gstu.courses.services;

import java.util.List;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseTopicsService {
    List<String> suggest(String name);
}
