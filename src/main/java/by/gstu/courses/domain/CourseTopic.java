package by.gstu.courses.domain;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @NaturalId
    private String name;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private boolean frozen;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "topics", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public CourseTopic() { }
    public CourseTopic(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTopic that = (CourseTopic) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
