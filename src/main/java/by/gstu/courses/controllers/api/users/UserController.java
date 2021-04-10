package by.gstu.courses.controllers.api.users;

import by.gstu.courses.controllers.AccountInfo;
import by.gstu.courses.dto.UserDto;
import by.gstu.courses.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * createdAt: 4/6/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAuthority(T(by.gstu.courses.domain.Permissions).VIEW_PROFILE.name())")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public UserDto getCurrentUser() {
        final long currentUserId = AccountInfo.getCurrentUserId();
        return userService.findUserById(currentUserId)
                .map(it -> modelMapper.map(it, UserDto.class))
                .orElseThrow(); // TODO: api level exception
    }
}
