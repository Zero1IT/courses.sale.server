package by.gstu.courses.dto;

import by.gstu.courses.validation.group.UpdateGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class CourseTopicDto {
    @Positive(groups = UpdateGroup.class)
    private Long id;
    @NotBlank
    private String name;
    private boolean approved;
    private boolean skipped;

    public CourseTopicDto() {
    }

    public CourseTopicDto(String name) {
        this.name = name;
    }
}
