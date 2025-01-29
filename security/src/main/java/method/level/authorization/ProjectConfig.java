package method.level.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ProjectConfig {

    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .authorities("read")
                .build();

        var user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("password"))
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.anyRequest().authenticated();
        });
        return http.build();
    }

    // Have other things PermissionEvaluatior, hasPermission


// @Secured ........ @RolesAllowed ..

//    @EnableMethodSecurity(
//            jsr250Enabled = true,
//            securedEnabled = true
//    )
//    @Service
//    public class NameService {
//
//        @RolesAllowed("ROLE_ADMIN")
//        public String getName() {
//            return "Fantastico";
//        }
//
//        @Secured("ROLE_ADMIN")
//        public String getName() {
//            return "Fantastico";
//        }
//    }



}
