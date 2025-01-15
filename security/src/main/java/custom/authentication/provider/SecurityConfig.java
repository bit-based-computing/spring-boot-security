package custom.authentication.provider;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {



    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final StaticCustomAuthenticationProvider staticCustomAuthenticationProvider;


    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider,
                          AuthenticationManager authenticationManager,
                          StaticCustomAuthenticationProvider staticCustomAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.staticCustomAuthenticationProvider = staticCustomAuthenticationProvider;
    }

    // custom authentication provider with basic Authentication
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//        http.authenticationProvider(customAuthenticationProvider);
//        http.authorizeHttpRequests(authorizeRequests -> {
//            authorizeRequests.anyRequest().authenticated();
//        });
//        return http.build();
//    }


    //multiple custom authentication provider with custom filter
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager  authenticationManager) throws Exception {
        http.addFilterAt(new CustomAuthenticationFilter(authenticationManager), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.anyRequest().authenticated();
        });
        return http.build();
    }



}
