package custom.authentication.provider;

import custom.filters.AuthenticationLoggingFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

public class CustomAuthenticationFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    private String authorizationKey = "test";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        var token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Basic ")) {
            checkBasicAuth(httpResponse, token);
        } else if (token != null) {
            checkStaticAuth(httpResponse, token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    void checkBasicAuth(HttpServletResponse response, String token) throws IOException {
        String[] words = token.split(" ");
        byte[] decodedBytes = Base64.getDecoder().decode(words[1]);

        // Convert bytes to string
        String usernameAndPassword = new String(decodedBytes);
        System.out.println(usernameAndPassword);
        words = usernameAndPassword.split(":");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(words[0], words[1]);

        try {
            // Pass to AuthenticationManager
            Authentication authenticated = authenticationManager.authenticate(authentication);

            // Set the authenticated object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticated);

        } catch (Exception ex) {
            // Handle authentication failure
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed: " + ex.getMessage());
        }
    }


    void checkStaticAuth(HttpServletResponse httpResponse, String token) throws IOException {
        StaticAuthentication authentication = new StaticAuthentication(token);

        try {
            // Pass to AuthenticationManager
            Authentication authenticated = authenticationManager.authenticate(authentication);

            // Set the authenticated object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticated);

        } catch (Exception ex) {
            // Handle authentication failure
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Authentication failed: " + ex.getMessage());
        }
    }

}

