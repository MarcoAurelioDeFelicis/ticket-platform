package it.madefelicis.ticket_platform.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.madefelicis.ticket_platform.model.Ticket;
import it.madefelicis.ticket_platform.response.Payload;
import it.madefelicis.ticket_platform.service.TicketService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket_platform")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

   
	
	@GetMapping("{id}")
	public ResponseEntity<Payload<Ticket>> get(@PathVariable("id") Integer idTicket) {
		
		Optional<Ticket> ticket = ticketService.findByid(idTicket);
		
		if(ticket.isPresent()) {
			return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));
		} else {
			return new ResponseEntity<Payload<Ticket>>(
					new Payload<Ticket>(null, "TICKET " + idTicket + " NOT FOUND", 
					HttpStatus.NOT_FOUND), 
					HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	
	//POST
	@PostMapping                         //BODY DELLA RICHIESTA//
	public ResponseEntity store (@Valid @RequestBody Ticket book) {
		
		try { 
			Ticket ticketSalvato = ticketService.save(book);
			return ResponseEntity.ok(ticketSalvato);
		} catch (Exception e) {
			return new ResponseEntity<>("ERROR DURING THE TICKET'S SAVING ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//PUT
	@PutMapping("{id}")
	public ResponseEntity update (@PathVariable("id")Integer idBook, 
			@Valid @RequestBody Ticket book ) {
		
		try {
			Ticket ticketkUpdate = ticketService.update(idBook, book);
						//SE UPDATE NON DA ERRORI
			return ResponseEntity.ok(ticketkUpdate);
		} catch (IllegalArgumentException e) {
			
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.NOT_FOUND); //ECCEZIONE CASO SBAGLIATO
		} catch (Exception e) {
			
			return new ResponseEntity<>("ERROR DURING THE TICKET'S SAVING ",
					HttpStatus.INTERNAL_SERVER_ERROR); // ECCEZZIONE QUALSIASI
		}
	}
	
	//DELETE
	@DeleteMapping("{id}") //OPZ
	public ResponseEntity <String> delete (@PathVariable("id")Integer id) {
		
		try {
			ticketService.delete(id);
			return ResponseEntity.ok("DELETE DONE!");
			
		}  catch (Exception e) {
			
			return new ResponseEntity<>("ERROR DURING THE TICKET'S SAVING ",
					HttpStatus.INTERNAL_SERVER_ERROR); // ECCEZZIONE QUALSIASI
		}
	}
}