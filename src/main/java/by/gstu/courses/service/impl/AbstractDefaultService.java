package by.gstu.courses.service.impl;

import by.gstu.courses.controller.api.Limits;
import by.gstu.courses.service.DefaultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public abstract class AbstractDefaultService<T, K> implements DefaultService<T> {

    private final JpaRepository<T, K> repository;

    AbstractDefaultService(JpaRepository<T, K> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getList(int page, int limit) {
        return getListOfEntities(page, limit, repository::findAll);
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    protected <E extends T> List<E> getListOfEntities(int page, int limit, Function<Pageable, Page<E>> func) {
        return func.apply(PageRequest.of(page-1, limit, Sort.Direction.DESC, "id"))
                .getContent();
    }
}
