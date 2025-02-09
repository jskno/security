package com.jskno.spring.resource.server2.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;

public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String tenantId = request.getHeader("X-Tenant-Id"); // (1)
        boolean hasAccess = isUserAllowed(tenantId); // (2)
        if (hasAccess) {
            filterChain.doFilter(request, response); // (3)
            return;
        }
        throw new AccessDeniedException("Access denied"); // (4)
    }

    /*
    	1. Get the tenant id from the request header.
        2. Check if the current user has access to the tenant id.
        3. If the user has access, then invoke the rest of the filters in the chain.
        4. If the user does not have access, then throw an AccessDeniedException.
     */

    private boolean isUserAllowed(String tenantId) {
        return "myOwnTenant".equals(tenantId);
    }

}