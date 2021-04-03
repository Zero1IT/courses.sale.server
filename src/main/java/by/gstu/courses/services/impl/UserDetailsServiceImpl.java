package by.gstu.courses.services.impl;

import by.gstu.courses.domain.Permissions;
import by.gstu.courses.domain.User;
import by.gstu.courses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Service("customDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // NOSONAR
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        final HashSet<Permissions.Permission> permissions = new HashSet<>(user.getRole().getPermissions());
        permissions.add(new Permissions.Permission(user.getRole().getWithPrefix()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                permissions
        );
    }
}
