package com.javainuse.springbootsecurity.config;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * The type Jwt util.
 */
@Slf4j
@Service
public class JwtUtil {

    private String secret;
    private int jwtExpirationInMs;

    /**
     * Sets secret.
     *
     * @param secret the secret
     */
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Sets jwt expiration in ms.
     *
     * @param jwtExpirationInMs the jwt expiration in ms
     */
    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    /**
     * Generate token string.
     *
     * @param userDetails the user details
     * @return the string
     */
    public String generateToken(UserDetails userDetails) {
        log.info("START JwtUtil.generateToken");
        Map<String, Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        if (roles.contains(new SimpleGrantedAuthority("ADMIN"))) {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("USER"))) {
            claims.put("isUser", true);
        }
        claims.put("UserDetails", userDetails);
        log.info("END JwtUtil.generateToken");

        return doGenerateToken(claims, userDetails);
    }

    private String doGenerateToken(Map<String, Object> claims, UserDetails subject) {
        log.info("Generating Token");

        return Jwts.builder()
                .setClaims(claims).
                setSubject(subject.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret)
//                .claim("user-details", subject)
                .compact();


    }

    /**
     * Validate token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public boolean validateToken(String authToken) {
        log.info("START JwtUtil.validateToken");

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            log.info("Valid Token");
            log.info("END JwtUtil.validateToken");
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Bad credentials");
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            log.info("Expired Token");
            throw ex;
        }

    }

    /**
     * Gets username from token.
     *
     * @param token the token
     * @return the username from token
     */
    public String getUsernameFromToken(String token) {
        log.info("START JwtUtil.getUsernameFromToken");
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        log.info("END JwtUtil.getUsernameFromToken");
        return claims.getSubject();

    }

    /**
     * Gets roles from token.
     *
     * @param token the token
     * @return the roles from token
     */
    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {

        log.info("START JwtUtil.getRolesFromToken");
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        List<SimpleGrantedAuthority> roles = null;

        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isUser = claims.get("isUser", Boolean.class);

        if (isAdmin != null && isAdmin) {
            log.info("UserType ADMIN");
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (isUser != null && isAdmin) {
            log.info("UserType USER");
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        log.info("END JwtUtil.getRolesFromToken");
        return roles;
    }


}
