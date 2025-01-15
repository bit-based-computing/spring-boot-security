package custom.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var requestId = httpRequest.getHeader("X-Request-ID");
        logger.info("Successfully authenticated Request ID: {}", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
