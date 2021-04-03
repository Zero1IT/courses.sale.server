package by.gstu.courses.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Data
@Entity
@Table(name = "course_topics")
public class CourseTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses = new HashSet<>();

    public CourseTopic() { }
    public CourseTopic(String name) {
        this.name = name;
    }
}
