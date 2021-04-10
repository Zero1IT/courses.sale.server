package by.gstu.courses.providers;

import by.gstu.courses.domain.Token;
import by.gstu.courses.domain.User;
import by.gstu.courses.exceptions.JwtAuthenticationException;
import by.gstu.courses.exceptions.ResourceItemNotFoundException;
import by.gstu.courses.repository.TokenRepository;
import by.gstu.courses.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * createdAt: 11/24/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    public static final String REFRESH_TOKEN_KEY = "refresh";
    public static final String ACCESS_TOKEN_KEY = "access";

    @Qualifier("customDetailsService")
    private final UserDetailsService detailsService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Value("${app.jwt.key}")
    private String secretKey;
    @Value("${app.jwt.expiration_sec}")
    private long tokenExpiration;
    @Value("${app.jwt.refresh_expiration_sec}")
    private long refreshExpiration;
    @Value("${app.jwt.header}")
    private String headerName;
    @Value("${app.jwt.prefix}")
    private String prefix;

    public Map<String, String> createTokens(User user) {
        final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        final Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN_KEY, createToken(user, key, tokenExpiration));
        tokens.put(REFRESH_TOKEN_KEY, createToken(user, key, refreshExpiration));
        return tokens;
    }

    public Map<String, String> createAndSaveTokens(String email) {
        return createAndSaveTokens(userRepository.findByEmail(email).orElseThrow(ResourceItemNotFoundException::new));
    }

    public Authentication getAuthentication(String token) {
        final Claims body = getBody(token);
        final UserDetails userDetails = detailsService.loadUserByUsername(getEmail(body));
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        authenticationToken.setDetails(getId(body));
        return authenticationToken;
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(headerName);
        return header == null || !header.startsWith(prefix) ? null : header.substring(prefix.length() + 1);
    }

    public Map<String, String> refreshToken(String rawToken) {
        String email = getEmail(rawToken);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new JwtAuthenticationException("user doesn't exists"));
        Token oldToken = tokenRepository.findByUser(user).orElseThrow(() ->
                new JwtAuthenticationException("Token not found"));
        if (rawToken.equals(oldToken.getRefreshJws())) {
            Map<String, String> tokens = createAndSaveTokens(user);
            oldToken.setRefreshJws(tokens.get(REFRESH_TOKEN_KEY));
            oldToken.setAccessJws(tokens.get(ACCESS_TOKEN_KEY));
            tokenRepository.save(oldToken);
            return tokens;
        }
        throw new JwtAuthenticationException("Invalid token");
    }

    public boolean validateToken(String token) {
        try {
            final Claims body = getBody(token);
            return tokenRepository.existsByUserIdAndAccessJws(getId(body), token);
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("Token is expired. Refresh it", HttpStatus.UNAUTHORIZED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Invalid token", HttpStatus.FORBIDDEN);
        }
    }

    private void saveToken(Map<String, String> tokens, User user) {
        Token token = tokenRepository.findByUser(user).orElse(new Token(user));
        token.setRefreshJws(tokens.get(REFRESH_TOKEN_KEY));
        token.setAccessJws(tokens.get(ACCESS_TOKEN_KEY));
        tokenRepository.save(token);
    }

    private Map<String, String> createAndSaveTokens(User user) {
        final Map<String, String> tokens = createTokens(user);
        saveToken(tokens, user);
        return tokens;
    }

    private String getEmail(String token) {
        return getBody(token).get("email", String.class);
    }

    private String getEmail(Claims body) {
        return body.get("email", String.class);
    }

    private Long getId(Claims body) {
        return body.get("id", Long.class);
    }

    private Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String createToken(User user, Key key, long expiration) {
        Date now = new Date();
        return Jwts.builder()
                .claim("role", user.getRole().getName())
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration * 1000))
                .signWith(key)
                .compact();
    }
}
