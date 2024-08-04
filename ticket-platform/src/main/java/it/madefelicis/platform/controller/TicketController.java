package it.madefelicis.platform.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.madefelicis.platform.model.Note;
import it.madefelicis.platform.model.Ticket;
import it.madefelicis.platform.model.User;
import it.madefelicis.platform.repository.CategoryRepository;
import it.madefelicis.platform.repository.NoteRepository;
import it.madefelicis.platform.repository.TicketRepository;
import it.madefelicis.platform.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping
	public String index(Model model, 
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "body", required = false) String body) {
		
		List<Ticket> tickets = new ArrayList<>();
		
		if(title == null && body == null) {
			tickets = ticketRepository.findAll();
		} else if (title == null){
            tickets = ticketRepository.findByBodyContainingIgnoreCase(body);
		}else {
			tickets = ticketRepository.findByTitleOrderByIdDesc(title);
		}
		model.addAttribute("list", tickets);
		
		Authentication autenticazine = SecurityContextHolder.getContext().getAuthentication();
        String username = autenticazine.getName();
        Optional<User> utente = userRepository.findByUsername(username);
        User user = utente.get();
        model.addAttribute("user", user);
		
		return "/tickets/index";
	}
	
	@GetMapping("/user")
    public String tasks (Model model,
    		@RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "body", required = false) String body) {

        List<Ticket> tickets = new ArrayList<>();

        Authentication autenticazine = SecurityContextHolder.getContext().getAuthentication();
        String username = autenticazine.getName();

        if (title == null && body == null) {

            tickets = ticketRepository.findByUserUsername(username);

        } else if (title == null) {
            tickets = ticketRepository.findByUserUsernameAndBodyContainingIgnoreCase(username, body);
        } else {

            tickets = ticketRepository.findByUserUsernameAndTitleContainingIgnoreCase(username, title);
        }
        model.addAttribute("list", tickets);

        Optional<User> utente = userRepository.findByUsername(username);
        User user = utente.get();
        model.addAttribute("user", user);


        return "/user/tasks";
    }
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
        Ticket ticket = ticketRepository.getReferenceById(id);
        model.addAttribute("ticket", ticket);
		
		return "/tickets/show";
	}
	
	@GetMapping("/create")
	public String create (Model model) {
		
		model.addAttribute("ticket", new Ticket());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("user", userRepository.findAll());
        List<User> utenteDisponibile = userRepository.findByStatusTrue();
        model.addAttribute("utenteDisponibile", utenteDisponibile);
		
		return "/tickets/create";
		
	}
	
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ticket") Ticket formTicket,
	   BindingResult bindingResult,Model model){
		
	   if(bindingResult.hasErrors()) {
          model.addAttribute("category", categoryRepository.findAll());
          List<User> utenteDisponibile = userRepository.findByStatusTrue();
          model.addAttribute("utenteDisponibile", utenteDisponibile);
          
	      return "/tickets/create";
	   }
	   
	   formTicket.setTicket_date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
	   formTicket.setStatus(Ticket.Status.DA_FARE);
       
	   ticketRepository.save(formTicket);

	   return "redirect:/tickets";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("ticket", ticketRepository.findById(id).get());
        model.addAttribute("status", Ticket.Status.values());

		
		return "/tickets/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("ticket") Ticket ticketUpdate,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
            model.addAttribute("status", Ticket.Status.values());

			return "/tickets/edit";
		}
		Ticket ticketEsistente = ticketRepository.findById(id).get();

		ticketEsistente.setTitle(ticketUpdate.getTitle());
		ticketEsistente.setBody(ticketUpdate.getBody());
		ticketEsistente.setStatus(ticketUpdate.getStatus());

        ticketRepository.save(ticketEsistente);

        return "redirect:/tickets";
    }
	
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		ticketRepository.deleteById(id);
		
		return "redirect:/tickets";
	}
	
	
//	@GetMapping("/{id}/note")
//	public String note(@PathVariable("id") Integer id, Model model) {
//		
//		Ticket ticket = ticketRepository.findById(id).get();
//		Note nota = new Note();
//		nota.setNoteDate(LocalDateTime.now());
//		nota.setTicket(ticket);
//		
//		Authentication autenticazione = SecurityContextHolder.getContext().getAuthentication();
//		String username = autenticazione.getName();
//		
//	    Optional<User> user = userRepository.findByUsername( username);
//	    User utenteloggato = user.get();
//		
//		nota.setAuthor(utenteloggato);
//		
//		model.addAttribute("nota", nota);
//        model.addAttribute("ticketId", id);
//		model.addAttribute("editMode", false);
//
//		return "/notes/edit";
//	}
	
	@GetMapping("/{id}/note")
    public String getCreateNote(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("ticketId", id);
        return "/notes/edit";
    }
	
	@PostMapping("/{id}/note")
    public String createNote(@PathVariable("id") Integer id,
    		@Valid Note note, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ticketId", id);
            return "/notes/edit";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        User utenteloggato = user.get();

        Ticket ticket = ticketRepository.getReferenceById(id);
        note.setTicket(ticket);
        note.setId(null);
        note.setNoteDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        note.setAuthor(utenteloggato);
        noteRepository.save(note);

        return "redirect:/tickets/" + id;
    }
	
	
	@ResponseBody
	@GetMapping("/tickets/{id}")
	public Ticket getTicketById(@PathVariable("id") Integer idTicket) {
		return ticketRepository.findById(idTicket).get();
	}

	
	
}
