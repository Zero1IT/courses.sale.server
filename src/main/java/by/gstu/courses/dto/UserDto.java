package by.gstu.courses.dto;

import by.gstu.courses.domain.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class UserDto {

    private Long id;
    private Role role;
    private String login;
    @Email
    private String email;
    private String phone;
    private String name;
    private String lastname;
    private boolean isConfirmed;
    private LecturerInfoDto lecturerInfo;
}
