package by.gstu.courses.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * createdAt: 1/27/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class UserRoleAssignDto {
    @Positive
    @NotNull
    private Long userId;
    @NotBlank
    @NotNull
    private String newRoleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNewRoleName() {
        return newRoleName;
    }

    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }
}
