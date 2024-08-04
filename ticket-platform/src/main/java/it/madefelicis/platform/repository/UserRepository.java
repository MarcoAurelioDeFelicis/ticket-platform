package it.madefelicis.platform.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.platform.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // METODO CHE TROVA GLI OPERATORI FREE
    List<User> findByStatusTrue();
}

