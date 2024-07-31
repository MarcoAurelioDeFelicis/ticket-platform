package it.madefelicis.ticket_platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//                 LOGICA CHE VIENE CARICATA ALLO START DELLA CATENA (CONFIG) DI AZIONI QUANDO SI LANCIA L'APP 

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
//	SPRING @BEANS --- PER IL PRERCARICAMENTO SU SERVER  //

	             
//	               LOGICA DI CHIAMATA INGRESSO HTTP
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
	throws Exception {
	http.authorizeHttpRequests()
    .requestMatchers("/tickets").hasAnyAuthority("ADMIN","USER")     //LISTA TICKETS
    .requestMatchers("/tickets/create", "/tickets/edit").hasAnyAuthority("USER")     //USER HOME AGGIUNGI TICKET  
    .requestMatchers("/tickets/show").hasAnyAuthority("USER","OPERATOR","ADMIN")     //DETTAGLIO TICKET
    .requestMatchers("/tickets/operators").hasAuthority("ADMIN")     //LISTA OPERATORI
    .requestMatchers("/tickets/notes/**").hasAnyAuthority("ADMIN", "OPERATORS")     //LISTA NOTE + AGGIUNGI NOTA
    .requestMatchers("/tasks").hasAuthority("OPERATOR")     //LISTA TASKS OPERATORE
	.requestMatchers("/**").permitAll()
	
	.and().formLogin()
	.and().logout()
	.and().exceptionHandling();

	return http.build();
	}
	
//	@Bean
//	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//		.requestMatchers("/books/create", "/books/edit/**").hasAuthority("ADMIN")
//		.requestMatchers(HttpMethod.POST, "/books/**").hasAuthority("ADMIN")
//		.requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")
//		.requestMatchers("/books", "/books/**").hasAnyAuthority("ADMIN", "USER")
////		AL DIFUORI DELLE REGOLE STABILITE SOPRA
//		.requestMatchers("/**").permitAll()
//		.and().formLogin()
//		.and().logout()
//		.and().exceptionHandling();
//		
//		return http.build();
//	
//	}
		
	@Bean
	DatabaseUserDetailsService userDetailsService() {
	return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	AUTENTICAZIONE SU DATABASE
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    
	return authProvider;
	}
	

}
