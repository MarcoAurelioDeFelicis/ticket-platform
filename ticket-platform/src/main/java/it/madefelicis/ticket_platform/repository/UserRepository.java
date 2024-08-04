package it.madefelicis.ticket_platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.ticket_platform.model.Ticket;
import it.madefelicis.ticket_platform.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	 Optional<User> findByUsername(String username);

	    //  PER OPERATORI DISPONIBILI
	    List<User> findByStatusTrue();

		List<Ticket> findByUsernameOrderByIdDesc(String userName);

}
