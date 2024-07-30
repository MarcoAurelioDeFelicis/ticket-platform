package it.madefelicis.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
