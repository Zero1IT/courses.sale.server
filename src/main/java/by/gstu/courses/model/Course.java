package by.gstu.courses.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * createdAt: 12/6/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Entity
@Table(name = "courses")
public class Course {
    private Long id;
    private String title;
    private String description;
    private Instant startDate;
    private short deferredPaymentDays;
    private boolean isClosed; // closed for invite
    private boolean isEnded;
    private Lecturer lecturer;

    private Set<User> users = new HashSet<>();
    private Set<CourseTopic> topics = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id", nullable = false)
    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @ManyToMany
    @JoinTable(
            name = "courses_to_users",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(
            name = "courses_to_topics",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    public Set<CourseTopic> getTopics() {
        return topics;
    }

    public void setTopics(Set<CourseTopic> topics) {
        this.topics = topics;
    }

    @Column(nullable = false)
    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    @Column(nullable = false)
    public short getDeferredPaymentDays() {
        return deferredPaymentDays;
    }

    public void setDeferredPaymentDays(short deferredPaymentDays) {
        this.deferredPaymentDays = deferredPaymentDays;
    }

    @Column(nullable = false)
    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Column(nullable = false)
    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }
}