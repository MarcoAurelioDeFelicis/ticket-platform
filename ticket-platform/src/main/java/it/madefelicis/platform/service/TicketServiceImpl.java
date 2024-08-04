package it.madefelicis.platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.madefelicis.platform.model.Ticket;
import it.madefelicis.platform.repository.TicketRepository;



@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAll() {
        
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findbyId(Integer id) {
        
        return ticketRepository.findById(id)
;
    }

    @Override
    public List<Ticket> findByCategoryName(String categoryName) {
        return ticketRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Ticket> findByStatus(Ticket.Status status) {
        return ticketRepository.findByStatus(status);
    }
}  