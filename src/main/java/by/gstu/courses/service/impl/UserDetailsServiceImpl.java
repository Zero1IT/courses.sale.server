package by.gstu.courses.service.impl;

import by.gstu.courses.model.Permissions;
import by.gstu.courses.model.User;
import by.gstu.courses.repository.UserRepository;
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
@Service("customDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
