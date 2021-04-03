package by.gstu.courses.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * createdAt: 11/22/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class AuthenticationDto {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private String repeatedPassword;
}
