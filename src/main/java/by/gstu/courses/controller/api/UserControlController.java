package by.gstu.courses.controller.api;

import by.gstu.courses.dto.LecturerDto;
import by.gstu.courses.dto.UserDto;
import by.gstu.courses.dto.UserRoleAssignDto;
import by.gstu.courses.exception.DataValidationException;
import by.gstu.courses.model.User;
import by.gstu.courses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * createdAt: 1/20/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole(T(by.gstu.courses.model.PermanentRoles).ADMIN.getWithPrefix())")
public class UserControlController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserControlController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping("role")
    public UserDto changeRole(@RequestBody @Valid UserRoleAssignDto userRoleAssign, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult);
        }

        final User user = userService.changeRole(userRoleAssign.getUserId(), userRoleAssign.getNewRoleName());
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    @GetMapping("{page}")
    public List<? extends UserDto> getUsers(@PathVariable int page,
                                         @RequestParam(name = "limit", defaultValue = "0") int limit,
                                         @RequestParam(name = "dtype", defaultValue = "all") String type) {
        limit = Limits.pageLimit(limit);
        Stream<? extends UserDto> stream;
        switch (type.toLowerCase()) {
            case "lecturer":
                stream = userService.getLecturerList(page, limit).stream()
                        .map(it -> modelMapper.map(it, LecturerDto.class));
                break;
            case "user":
                stream = userService.getUsersList(page, limit).stream()
                        .map(it -> modelMapper.map(it, UserDto.class));
                break;
            default:
                stream = userService.getList(page, limit).stream()
                        .map(it -> modelMapper.map(it, UserDto.class));
                break;
        }

        return stream.collect(Collectors.toList());
    }

    @GetMapping("select")
    public Page<? extends UserDto> getUsers(Pageable pageable,
                                            @RequestParam(name = "email", required = false) String email,
                                            @RequestParam(name = "dtype", defaultValue = "all") String type) {
        Page<? extends UserDto> page;
        switch (type.toLowerCase()) {
            case "lecturer":
                page = userService.getLecturePage(pageable, email)
                        .map(it -> modelMapper.map(it, LecturerDto.class));
                break;
            case "user":
                page = userService.getUsersPage(pageable, email)
                        .map(it -> modelMapper.map(it, UserDto.class));
                break;
            default:
                page = userService.getPageByEmail(pageable, email)
                        .map(it -> modelMapper.map(it, UserDto.class));
                break;
        }

        return page;
    }
}
