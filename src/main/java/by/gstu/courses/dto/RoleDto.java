package by.gstu.courses.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * createdAt: 4/11/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Getter
@Setter
public class RoleDto {
    @NotBlank
    public String name;
    @NotEmpty
    public Set<String> permissions;
}
