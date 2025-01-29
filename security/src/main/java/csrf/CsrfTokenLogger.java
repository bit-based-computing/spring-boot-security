package csrf;


import jakarta.servlet.*;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;
import java.util.logging.Logger;

public class CsrfTokenLogger implements Filter {



    private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CsrfToken token = (CsrfToken) servletRequest.getAttribute(CsrfToken.class.getName());
        logger.info("Csrf token: " + token.getToken());
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
