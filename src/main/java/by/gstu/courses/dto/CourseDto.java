package by.gstu.courses.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.time.Instant;

/**
 * createdAt: 1/19/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class CourseDto {
    @PositiveOrZero
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Future
    @NotNull
    private Instant startDate;
    @PositiveOrZero
    @NotNull
    private Short deferredPaymentDays;
    @Positive
    @NotNull
    private Short places;

    // ignored for deserialization
    private boolean isClosed;
    private boolean isEnded;
    private Long lecturerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Short getDeferredPaymentDays() {
        return deferredPaymentDays;
    }

    public void setDeferredPaymentDays(Short deferredPaymentDays) {
        this.deferredPaymentDays = deferredPaymentDays;
    }

    public Short getPlaces() {
        return places;
    }

    public void setPlaces(Short places) {
        this.places = places;
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
