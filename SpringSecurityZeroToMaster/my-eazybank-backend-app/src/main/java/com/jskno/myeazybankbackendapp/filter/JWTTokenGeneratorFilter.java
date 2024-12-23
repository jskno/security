package com.jskno.myeazybankbackendapp.filter;

import com.jskno.myeazybankbackendapp.config.CustomPrincipal;
import com.jskno.myeazybankbackendapp.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            // The JWT shouldn´t contain the user password.
            String jwt = Jwts.builder()
                .setIssuer("EazyBank")
                .setSubject("JWT Token")
                .claim("username", authentication.getName())
                .claim("customerId", ((CustomPrincipal) authentication.getPrincipal()).getCustomerId())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 30_000_000))
//                .setExpiration(new Date(new Date().getTime() + 30_000))
                .signWith(key)
                .compact();
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // This generation JWT process should only be triggered in the login page
        // when the user is trying to access the app
        return !request.getServletPath().equals("/user");
    }

    private Object populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return String.join(",", authorities.stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
    }
}
