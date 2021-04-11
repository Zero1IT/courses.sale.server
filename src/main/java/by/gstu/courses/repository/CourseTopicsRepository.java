package by.gstu.courses.repository;

import by.gstu.courses.domain.CourseTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface CourseTopicsRepository extends JpaRepository<CourseTopic, Long> {
    Set<CourseTopic> findByNameIn(Collection<String> name);
    @Query("select ct.name from CourseTopic ct where ct.approved=true and ct.name like :value%")
    List<String> suggestNames(String value);
    Page<CourseTopic> findAllByApprovedFalseAndSkippedFalse(Pageable pageable);
    Page<CourseTopic> findAllByApprovedFalseAndSkippedTrue(Pageable pageable);
    Page<CourseTopic> findAllByApprovedTrue(Pageable pageable);
}
