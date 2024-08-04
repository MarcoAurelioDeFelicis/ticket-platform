package it.madefelicis.platform.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.madefelicis.platform.model.Ticket;
import it.madefelicis.platform.response.Payload;
import it.madefelicis.platform.service.TicketService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/api/platform")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<Payload<List<Ticket>>> getAll() {
        List<Ticket> tickets = ticketService.findAll();
        return ResponseEntity.ok(new Payload<List<Ticket>>(tickets, null, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payload<Ticket>> getId(@PathVariable("id") Integer ticketId) {

        Optional<Ticket> ticket = ticketService.findbyId(ticketId);

        if (ticket.isPresent()) {
            return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));

        } else {
            return new ResponseEntity<Payload<Ticket>>(
                    new Payload<Ticket>(null, "Ticket con id " + ticketId + " non trovato", HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Payload<List<Ticket>>> getByCategoryName(@PathVariable("categoryName") String categoryName) {
        List<Ticket> tickets = ticketService.findByCategoryName(categoryName);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(
                    new Payload<>(null, "Nessun ticket trovato per la categoria " + categoryName, HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Payload<>(tickets, null, HttpStatus.OK));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Payload<List<Ticket>>> getByStatus(@PathVariable("status") Ticket.Status status) {
        List<Ticket> tickets = ticketService.findByStatus(status);
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(
                    new Payload<>(null, "Nessun ticket trovato per lo status " + status, HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Payload<>(tickets, null, HttpStatus.OK));
    }
}