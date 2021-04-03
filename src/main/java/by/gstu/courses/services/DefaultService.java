package by.gstu.courses.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface DefaultService<T> {
    List<T> getList(int page, int limit);
    Page<T> getPage(Pageable pageable);
}
