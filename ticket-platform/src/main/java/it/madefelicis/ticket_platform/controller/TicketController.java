package it.madefelicis.ticket_platform.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.madefelicis.ticket_platform.model.Category;
import it.madefelicis.ticket_platform.model.Ticket;
import it.madefelicis.ticket_platform.repository.CategoryRepository;
import it.madefelicis.ticket_platform.repository.NoteRepository;
import it.madefelicis.ticket_platform.repository.TicketRepository;
import it.madefelicis.ticket_platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/newticket")
public class TicketController {
	
	@Autowired
    TicketRepository ticketRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;
    
//     CREAZIONE TICKET
    
//    @GetMapping
//	public String index(Model model, 
//			@RequestParam(name = "userName", required = false) String userName) {
//		
//		List<Ticket> libri = new ArrayList<>();
//		
//		if(author == null || author.isBlank()) {
//			libri = bookRepository.findAll();
//		} else {
//			libri = bookRepository.findByAuthorOrderByIdDesc(author);
//		}
//		
//		model.addAttribute("list", libri);
//		
//		return "/newTicket/create";
//	}
    
    @GetMapping("newticket/create")
	public String create (Model model, 
			@RequestParam(name = "userName", required = false) String userName) {
    	
    	List<Ticket> ticket = new ArrayList<>();
    	
    	if(userName == null || userName.isBlank()) {
			ticket = ticketRepository.findAll();
		} else {
			ticket = userRepository.findByUsernameOrderByIdDesc(userName);
		}
		model.addAttribute("listaTicket",ticketRepository.findAll());
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("listacategories", categoryRepository.findAll());
		model.addAttribute("categoria", new Category());
        model.addAttribute("users", userRepository.findAll());

		
		return "/newticket/create";
		
	}
    
    @PostMapping("newticket/create")
	public String store(@Valid @ModelAttribute("ticket") Ticket formTicket,
	   BindingResult bindingResult,Model model){
		
	   if(bindingResult.hasErrors()) {
           model.addAttribute("category", categoryRepository.findAll());
	      return "/newticket/create";
	   }
	   
	   formTicket.setTicket_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
	   formTicket.setStatus(Ticket.Status.da_fare);

       ticketRepository.save(formTicket);
	   return "/newticket/create";
	}
    
    @GetMapping("newticket/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("ticket", ticketRepository.findById(id).get());
		
		return "/newticket/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("ticket") Ticket ticket,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/newticket/edit";
		}
		
		ticketRepository.save(ticket);
		
		return "redirect:/newticket/create";
	}
    
    @PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		ticketRepository.deleteById(id);
		
		return "redirect:/newticket/create";
	}
    
    
    

}
