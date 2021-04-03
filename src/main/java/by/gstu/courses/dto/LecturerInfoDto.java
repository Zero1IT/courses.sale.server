package by.gstu.courses.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * createdAt: 2/3/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class LecturerInfoDto {
    private long id;
    private boolean canPublish = true;
    private long userId;
}
