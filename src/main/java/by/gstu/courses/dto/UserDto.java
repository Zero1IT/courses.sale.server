package by.gstu.courses.dto;

import by.gstu.courses.domain.Role;
import by.gstu.courses.validation.group.UpdateGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class UserDto {

    @Positive(groups = UpdateGroup.class)
    private Long id;
    @NotNull
    private Role role;
    private String login;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    private boolean isConfirmed;
    private LecturerInfoDto lecturerInfo;
}
