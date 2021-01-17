package by.gstu.courses.config.security;

import by.gstu.courses.provider.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;

    public SecurityConfig(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .requiresChannel()
                    .anyRequest()
                    .requiresSecure()
                .and()
                    .cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/auth/registration", "/api/auth/auto").permitAll()
                    .antMatchers(HttpMethod.GET, "/verify/*", "/activate/*").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager(), tokenProvider))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenProvider))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}