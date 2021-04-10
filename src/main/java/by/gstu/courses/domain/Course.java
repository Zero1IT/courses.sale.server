package by.gstu.courses.domain;

import by.gstu.courses.Limits;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * createdAt: 12/6/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Instant startDate;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private Short places;

    @Column(nullable = false)
    private boolean closed; // closed for invite

    @Column(nullable = false)
    private boolean ended;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "courses_to_users",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(
            name = "courses_to_topics",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    @BatchSize(size = Limits.MAX_TOPICS_PER_COURSE)
    private Set<CourseTopic> topics = new HashSet<>();
}
