package it.madefelicis.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import it.madefelicis.ticket_platform.model.Ticket;

public interface TicketService {
	
	public Optional<Ticket> findByid(Integer Id);
	public List<Ticket> findByCategoryName(String categoryName);
	
	List<Ticket> findAll();
	
//	SALVO IL TICKET PASSATO COME ARGOMENTO (SENZA ID)
//	E RESTITUISCO L'ELEMENTO APPENA SALVATO (CON ID DATO DAL DB)
	
	public Ticket save (Ticket ticket);
	
	public Ticket update (Integer id, Ticket ticket);
	
	public void delete (Integer id);



	
	
	
}
