package com.library.api.config.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        Optional<String> authHeaderOptional = Optional.ofNullable(request.getHeader("Authorization"));
        String authToken;

        try {
            if (authHeaderOptional.isPresent()) {
                authToken = authHeaderOptional.get().substring(7);

                String username = this.jwtUtil.getUsernameFromToken(authToken);
                if (username != null && this.jwtUtil.isValidToken(authToken)) {
                    Claims claims = this.jwtUtil.getAllClaimsFromToken(authToken);
                    List<Map<String, Object>> authorities = (List)claims.get("roles", ArrayList.class);
                    Map<String, Object> otherDetails = new HashMap();

                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, username,
                            authorities.stream()
                                                    .map((m) -> new SimpleGrantedAuthority((String) m.get("authority"))
                                                        ).collect(Collectors.toSet()));

                    user.setDetails(otherDetails);

                    SecurityContextHolder.getContext().setAuthentication(user);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    public JwtRequestFilter(final JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
}
