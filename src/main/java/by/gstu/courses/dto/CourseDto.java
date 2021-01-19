package by.gstu.courses.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class CourseDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Future
    @NotNull
    private Instant startDate;
    @PositiveOrZero
    private short deferredPaymentDays;

    // ignored for deserialization
    private boolean isClosed;
    private boolean isEnded;
    private Long lecturerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public short getDeferredPaymentDays() {
        return deferredPaymentDays;
    }

    public void setDeferredPaymentDays(short deferredPaymentDays) {
        this.deferredPaymentDays = deferredPaymentDays;
    }

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
