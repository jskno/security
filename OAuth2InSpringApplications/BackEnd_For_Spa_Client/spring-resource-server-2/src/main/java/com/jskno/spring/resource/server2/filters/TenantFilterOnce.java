package com.jskno.spring.resource.server2.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TenantFilterOnce extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String tenantId = request.getHeader("X-Tenant-Id"); // (1)
        boolean hasAccess = isUserAllowed(tenantId); // (2)
        if (hasAccess) {
            filterChain.doFilter(request, response); // (3)
            return;
        }
        throw new AccessDeniedException("Access denied"); // (4)
    }

    private boolean isUserAllowed(String tenantId) {
        return "myOwnTenant".equals(tenantId);
    }

}