package by.gstu.courses.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * createdAt: 2/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Data
@Entity
@Table(name = "lecturer_info")
public class LecturerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean canPublish = true;

    @OneToOne(mappedBy = "lecturerInfo")
    private User user;

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    private Set<Course> ownCourses;
}
