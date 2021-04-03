package by.gstu.courses.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * createdAt: 1/27/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class UserRoleAssignDto {
    @Positive
    @NotNull
    private Long userId;
    @NotBlank
    @NotNull
    private String newRoleName;
}
