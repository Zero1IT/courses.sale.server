package by.gstu.courses.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * createdAt: 3/21/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollResponse {
    private long userId;
    private long courseId;
    private boolean enrolled;
}
