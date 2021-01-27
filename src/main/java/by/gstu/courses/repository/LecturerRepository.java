package by.gstu.courses.repository;

import by.gstu.courses.model.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query("select u from Lecturer u where u.email=:email")
    Lecturer getOne(String email);
    Page<Lecturer> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
