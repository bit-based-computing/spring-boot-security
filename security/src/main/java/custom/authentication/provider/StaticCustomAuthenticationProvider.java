package custom.authentication.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class StaticCustomAuthenticationProvider implements AuthenticationProvider {
    @Value("${authorization.key}")
    private String authorizationKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        if (authorizationKey.equals(username)) {
            return new StaticAuthentication(username);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(StaticAuthentication.class);
    }
}
