package it.madefelicis.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.madefelicis.ticket_platform.model.Ticket;
import it.madefelicis.ticket_platform.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
//	GET LIST
	@Override
    public List<Ticket> findAll() {
        
        return ticketRepository.findAll();
    }

//	GET
	@Override
	public Optional<Ticket> findByid(Integer Id) {
		return ticketRepository.findById(Id);
	}
	
//	POST
	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
//	UPDATE
	@Override
	public Ticket update(Integer id, Ticket inputTicket) {
		
		Optional<Ticket> ticketCercato = ticketRepository.findById(id);
		
//		ECCEZIONE IN CASO DI DATO SBAGLIATO
		
		if (ticketCercato.isEmpty()) {
			
			throw new IllegalArgumentException("THE TICKET"+id+"DOESN'T EXIST");
		} 
		
//		CONTROLLO DELLA SAVE
		
		Ticket ticket = ticketCercato.get();
		
		ticket.setNote(inputTicket.getNote());
		ticket.setBody(inputTicket.getBody());
		ticket.setCategory(inputTicket.getCategory());
//		ticket.setId(inputTicket.getId()); NON ID
		ticket.setStatus(inputTicket.getStatus());
		ticket.setNote(inputTicket.getNote());
		ticket.setTitle(inputTicket.getTitle());
		
		return ticketRepository.save(ticket);
	}
	
//	DELETE
	@Override
	public void delete(Integer id) {
		ticketRepository.deleteById(id);
	}

    @Override
    public List<Ticket> findByCategoryName(String categoryName) {
	return null;
}

}
