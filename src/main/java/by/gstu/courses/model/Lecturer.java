package by.gstu.courses.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Entity
public class Lecturer extends User {
    private boolean canPublish = true;

    private Set<Course> ownCourses;

    public boolean isCanPublish() {
        return canPublish;
    }

    public void setCanPublish(boolean canPublish) {
        this.canPublish = canPublish;
    }

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    public Set<Course> getOwnCourses() {
        return ownCourses;
    }

    public void setOwnCourses(Set<Course> courses) {
        this.ownCourses = courses;
    }
}
