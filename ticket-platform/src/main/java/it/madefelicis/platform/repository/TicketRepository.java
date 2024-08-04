package it.madefelicis.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.madefelicis.platform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer > {
	
   /*
	* SELECT *
	* FROM book b
	* WHERE b.author =?
	*/
	
	public List<Ticket> findByTitle(String title);
	
	public List<Ticket> findByTitleOrderByIdDesc(String title);

	public List<Ticket> findByBodyContainingIgnoreCase(String body);
	
	@Query("SELECT t FROM Ticket t WHERE t.category.name = :categoryName")
    List<Ticket> findByCategoryName(@Param("categoryName") String categoryName);

    List<Ticket> findByStatus(Ticket.Status status);

    List<Ticket> findByUserUsername(String username);

	public List<Ticket> findByUserUsernameAndBodyContainingIgnoreCase(String username, String body);
	
    List<Ticket> findByUserUsernameAndTitleContainingIgnoreCase(String username, String title);
    
    int countByUserIdAndStatusIn(Integer userId, List<String> status);


}
