package by.gstu.courses.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
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
    @PositiveOrZero
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private BigDecimal cost;
    private String description;
    @Future
    @NotNull
    private Instant startDate;
    @Positive
    @NotNull
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
