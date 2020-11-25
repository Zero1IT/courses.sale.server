package by.gstu.edu.provider;

import by.gstu.edu.exception.JwtAuthenticationException;
import by.gstu.edu.model.Token;
import by.gstu.edu.model.User;
import by.gstu.edu.repository.TokenRepository;
import by.gstu.edu.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenProvider {

    public static final String REFRESH_TOKEN_KEY = "refresh";
    public static final String ACCESS_TOKEN_KEY = "access";

    private final UserDetailsService detailsService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Value("${jwt.key}")
    private String secretKey;
    @Value("${jwt.expiration_sec}")
    private long tokenExpiration;
    @Value("${jwt.refresh_expiration_sec}")
    private long refreshExpiration;
    @Value("${jwt.header}")
    private String headerName;
    @Value("${jwt.prefix}")
    private String prefix;

    public JwtTokenProvider(@Qualifier("customDetailsService") UserDetailsService detailsService, UserRepository userRepository, TokenRepository tokenRepository) {
        this.detailsService = detailsService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Map<String, String> createTokens(User user) {
        final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        final Map<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN_KEY, createToken(user, key, tokenExpiration));
        tokens.put(REFRESH_TOKEN_KEY, createToken(user, key, refreshExpiration));
        return tokens;
    }

    public Map<String, String> createAndSaveTokens(String email) {
        return createAndSaveTokens(userRepository.findByEmail(email)
                .orElseThrow( /* undefined behavior */ ));
    }

    public Map<String, String> createAndSaveTokens(User user) {
        final Map<String, String> tokens = createTokens(user);
        saveRefreshToken(tokens, user);
        return tokens;
    }

    public Authentication getAuthentication(String token) {
        final UserDetails userDetails = detailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
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
        if (rawToken.equals(oldToken.getJws())) {
            Map<String, String> tokens = createAndSaveTokens(user);
            oldToken.setJws(tokens.get(REFRESH_TOKEN_KEY));
            tokenRepository.save(oldToken);
            return tokens;
        }
        throw new JwtAuthenticationException("Invalid token");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("Token is expired. Refresh it", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException("Invalid token", HttpStatus.FORBIDDEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }

    public void saveRefreshToken(Map<String, String> tokens, User user) {
        final String refresh = tokens.get(REFRESH_TOKEN_KEY);
        Token token = tokenRepository.findByUser(user).orElse(new Token(user));
        token.setJws(refresh);
        tokenRepository.save(token);
    }

    private String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    private String createToken(User user, Key key, long expiration) {
        Date now = new Date();
        return Jwts.builder()
                .claim("role", user.getRole().name())
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration * 1000))
                .signWith(key)
                .compact();
    }
}
