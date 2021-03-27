package by.gstu.courses.dto.response;

/**
 * createdAt: 3/21/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class EnrollResponse {
    private long userId;
    private long courseId;
    private boolean enrolled;

    public EnrollResponse() {
    }

    public EnrollResponse(long userId, long courseId, boolean enrolled) {
        this.userId = userId;
        this.courseId = courseId;
        this.enrolled = enrolled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }
}
