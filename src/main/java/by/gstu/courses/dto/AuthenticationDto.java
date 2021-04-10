package by.gstu.courses.dto;

import by.gstu.courses.validation.constraints.PasswordEqualsConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
@PasswordEqualsConstraint
public class AuthenticationDto {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @Email
    private String email;
    @NotBlank
    private String phone;

    private String password;
    private String confirmedPassword;
}
