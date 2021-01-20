package by.gstu.courses.controller.api;

import by.gstu.courses.dto.LecturerDto;
import by.gstu.courses.dto.UserDto;
import by.gstu.courses.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{page}")
    public List<? extends UserDto> getUsers(@PathVariable int page,
                                         @RequestParam(name = "limit", defaultValue = "0") int limit,
                                         @RequestParam(name = "type", defaultValue = "all") String type) {
        if (limit <= 0 || limit > 20) {
            limit = 20;
        }

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
}
