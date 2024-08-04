package it.madefelicis.platform.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.madefelicis.platform.model.User;
import it.madefelicis.platform.repository.UserRepository;


@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) 
    		throws UsernameNotFoundException {
    
        Optional<User> utente = userRepository.findByUsername(username);

        if (utente.isPresent()) {
            return new DatabaseUserDetails(utente.get());
        } else {
            throw new UsernameNotFoundException("USERNAME NOT FOUND");
        }

    }

}
