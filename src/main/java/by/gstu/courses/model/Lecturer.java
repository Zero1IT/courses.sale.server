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
    private boolean canPublish;

    private Set<Course> courses;

    public boolean isCanPublish() {
        return canPublish;
    }

    public void setCanPublish(boolean canPublish) {
        this.canPublish = canPublish;
    }

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
