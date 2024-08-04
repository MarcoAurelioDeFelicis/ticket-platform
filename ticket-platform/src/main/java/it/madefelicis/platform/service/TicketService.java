package it.madefelicis.platform.service;

import java.util.List;
import java.util.Optional;

import it.madefelicis.platform.model.Ticket;



public interface TicketService {
	
	public Optional<Ticket> findbyId(Integer id);

    public List<Ticket> findAll();

    public List<Ticket> findByCategoryName(String categoryName);

    public List<Ticket> findByStatus(Ticket.Status status);
}