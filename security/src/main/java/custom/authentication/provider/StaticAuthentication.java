package custom.authentication.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class StaticAuthentication extends AbstractAuthenticationToken {

    private String username;
    public StaticAuthentication(String username) {
        super(List.of(new SimpleGrantedAuthority("USER")));
        this.username = username;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "null";
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }
}
