package custom.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StaticKeyAuthenticationFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);

    @Value("${authorization.key}")
    private String authorizationKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        var authorizationHeader = httpRequest.getHeader("Authorization");
        logger.info("Authorization header: {}", authorizationHeader);
        if (authorizationHeader.equals(authorizationKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
