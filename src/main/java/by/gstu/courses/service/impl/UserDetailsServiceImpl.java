package by.gstu.courses.service.impl;

import by.gstu.courses.model.User;
import by.gstu.courses.repository.UserRepository;
import by.gstu.courses.service.AuthenticateService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service("customDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticateService authenticateService;

    public UserDetailsServiceImpl(UserRepository userRepository, AuthenticateService authenticateService) {
        this.userRepository = userRepository;
        this.authenticateService = authenticateService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // NOSONAR
        final User user = userRepository.findByEmail(email)
                .orElseGet(() -> authenticateService.transferTempUser(email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getRole().getPermissions()
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet())
        );
    }
}
