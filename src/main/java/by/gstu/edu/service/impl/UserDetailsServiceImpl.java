package by.gstu.edu.service.impl;

import by.gstu.edu.model.User;
import by.gstu.edu.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isConfirmed(),
                user.isConfirmed(),
                user.isConfirmed(),
                user.isConfirmed(),
                user.getRole().getPermissions()
        );
    }
}
