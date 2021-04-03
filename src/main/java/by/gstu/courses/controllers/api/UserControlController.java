package by.gstu.courses.controllers.api;

import by.gstu.courses.controllers.Limits;
import by.gstu.courses.dto.UserDto;
import by.gstu.courses.dto.UserRoleAssignDto;
import by.gstu.courses.exceptions.DataValidationException;
import by.gstu.courses.domain.User;
import by.gstu.courses.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole(T(by.gstu.courses.domain.PermanentRoles).ADMIN.getWithPrefix())")
public class UserControlController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping("role")
    public UserDto changeRole(@RequestBody @Valid UserRoleAssignDto userRoleAssign, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult);
        }

        final User user = userService.changeRole(userRoleAssign.getUserId(), userRoleAssign.getNewRoleName());
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    @GetMapping("{page}")
    public List<UserDto> getUsers(@PathVariable int page,
                                         @RequestParam(name = "limit", defaultValue = "0") int limit,
                                         @RequestParam(name = "dtype", defaultValue = "all") String type) {
        limit = Limits.pageLimit(limit);
        List<User> users;
        switch (type.toLowerCase()) {
            case "lecturer":
                users = userService.getLecturerList(page, limit);
                break;
            case "user":
                users = userService.getUsersList(page, limit);
                break;
            default:
                users = userService.getList(page, limit);
                break;
        }

        return users.stream()
                .map(it -> modelMapper.map(it, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("select")
    public Page<UserDto> getUsers(Pageable pageable,
                                            @RequestParam(name = "email", required = false) String email,
                                            @RequestParam(name = "dtype", defaultValue = "all") String type) {
        Page<User> page;
        switch (type.toLowerCase()) {
            case "lecturer":
                page = userService.getLecturePage(pageable, email);
                break;
            case "user":
                page = userService.getUsersPage(pageable, email);
                break;
            default:
                page = userService.getPageByEmail(pageable, email);
                break;
        }

        return page.map(it -> modelMapper.map(it, UserDto.class));
    }
}
