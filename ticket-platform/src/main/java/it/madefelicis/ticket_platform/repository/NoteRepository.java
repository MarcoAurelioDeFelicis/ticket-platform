package it.madefelicis.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.ticket_platform.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	
     List<Note> findByTicketIdOrderByNoteDateDesc(Integer ticketId);


}
