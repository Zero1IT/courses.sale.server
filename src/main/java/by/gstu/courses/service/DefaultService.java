package by.gstu.courses.service;

import java.util.List;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface DefaultService<T> {
    List<T> getList(int page, int limit);
}
