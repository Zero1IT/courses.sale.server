package by.gstu.courses.repository;

import by.gstu.courses.domain.CourseTopic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseTopicsRepository extends Repository<CourseTopic, Long> {
    Set<CourseTopic> findByNameIn(Collection<String> name);
    @Query("select ct.name from CourseTopic ct where ct.name like :value%")
    List<String> suggestNames(String value);
}
