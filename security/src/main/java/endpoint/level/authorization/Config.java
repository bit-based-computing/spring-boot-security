package endpoint.level.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Config {
    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                .password("password")
                .authorities("READ").build();
        var user2 = User.withUsername("user2")
                .password("password")
                .authorities("WRITE").build();
        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
