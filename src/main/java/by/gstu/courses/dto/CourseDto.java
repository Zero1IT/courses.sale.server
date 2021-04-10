package by.gstu.courses.dto;

import by.gstu.courses.validation.group.UpdateGroup;
import by.gstu.courses.validation.constraints.NotNullFuture;
import by.gstu.courses.validation.constraints.NotNullPositive;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class CourseDto {
    @NotNullPositive(groups = UpdateGroup.class)
    private Long id;
    @NotBlank
    private String title;
    @NotNullPositive
    private BigDecimal cost;
    private String description;
    @NotNullFuture
    private Instant startDate;
    @NotNullPositive
    private Short places;
    private String imgUrl;
    private Set<CourseTopicDto> topics;
    
    // ignored for deserialization
    private boolean isClosed;
    private boolean isEnded;
    private Long lecturerId;

    @JsonProperty
    public boolean isClosed() {
        return isClosed;
    }

    @JsonIgnore
    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @JsonProperty
    public boolean isEnded() {
        return isEnded;
    }

    @JsonIgnore
    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    @JsonProperty
    public Long getLecturerId() {
        return lecturerId;
    }

    @JsonIgnore
    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }
}
