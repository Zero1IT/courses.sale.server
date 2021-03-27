package by.gstu.courses.model;

import javax.persistence.*;
import java.util.Set;

/**
 * createdAt: 2/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Entity
@Table(name = "lecturer_info")
public class LecturerInfo {
    private long id;
    private boolean canPublish = true;

    private User user;
    private Set<Course> ownCourses;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCanPublish() {
        return canPublish;
    }

    public void setCanPublish(boolean canPublish) {
        this.canPublish = canPublish;
    }

    @OneToOne(mappedBy = "lecturerInfo")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    public Set<Course> getOwnCourses() {
        return ownCourses;
    }

    public void setOwnCourses(Set<Course> courses) {
        this.ownCourses = courses;
    }
}
