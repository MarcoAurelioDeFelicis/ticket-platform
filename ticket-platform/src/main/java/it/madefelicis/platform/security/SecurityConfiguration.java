package it.madefelicis.platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/tickets/**").permitAll()
                .requestMatchers("/tickets/user/**").hasAuthority("USER")
                .requestMatchers("/user/**").hasAuthority("USER")
                .requestMatchers("/tasks/**").hasAuthority("USER")
                .requestMatchers("/notes/**").hasAuthority("ADMIN")
                .requestMatchers("/tickets/create").hasAuthority("ADMIN")
                .requestMatchers("/tickets/edit").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/tickets/{id}/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/tickets/**").hasAnyAuthority("ADMIN", "USER")
                .and()
                .formLogin()
                .and()
                .logout()
                .and().exceptionHandling();

        return http.build();
    }
}
