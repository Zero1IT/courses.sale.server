package by.gstu.courses.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * createdAt: 4/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class CourseTopicDto {
    private String name;

    public CourseTopicDto() {
    }

    public CourseTopicDto(String name) {
        this.name = name;
    }
}
