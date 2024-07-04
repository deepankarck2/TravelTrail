package com.traveltrail.backend.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private static final int JWT_TOKEN_POSITION = 7;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = getAuthHeader(request);

        if (isInvalidAuthHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = extractJwtFromHeader(authHeader);
        String userEmail = jwtTokenUtil.extractUserName(jwt);

        if (shouldAuthenticateUser(userEmail)) {
            authenticateUser(request, jwt, userEmail);
        }

        filterChain.doFilter(request, response);
    }

    private String getAuthHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isInvalidAuthHeader(String authHeader) {
        return authHeader == null || !authHeader.startsWith("Bearer ");
    }

    private String extractJwtFromHeader(String authHeader) {
        return authHeader.substring(JWT_TOKEN_POSITION);
    }

    private boolean shouldAuthenticateUser(String userEmail) {
        return userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void authenticateUser(HttpServletRequest request, String jwt, String userEmail) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        if (jwtTokenUtil.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
}
