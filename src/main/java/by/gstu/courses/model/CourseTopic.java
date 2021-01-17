package by.gstu.courses.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * createdAt: 1/17/2021
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Entity
@Table(name = "course_topics")
public class CourseTopic {
    private Long id;
    private String name;

    private Set<Course> courses = new HashSet<>();

    public CourseTopic() { }

    public CourseTopic(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "topics")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
