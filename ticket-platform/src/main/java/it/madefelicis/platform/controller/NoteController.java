////package it.madefelicis.platform.controller;
////
////import java.time.LocalDateTime;
////import java.time.temporal.ChronoUnit;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.validation.BindingResult;
////import org.springframework.validation.ObjectError;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PathVariable;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////
////import it.madefelicis.platform.model.Note;
////import it.madefelicis.platform.model.Ticket;
////import it.madefelicis.platform.model.User;
////import it.madefelicis.platform.repository.NoteRepository;
////import it.madefelicis.platform.repository.TicketRepository;
////import it.madefelicis.platform.repository.UserRepository;
////import jakarta.validation.Valid;
////
////@Controller
////@RequestMapping("/notes")
////public class NoteController {
////
////	@Autowired
////	private NoteRepository noteRepository;
////	
////	@Autowired
////	private TicketRepository ticketRepository;
////	
////	@Autowired
////    UserRepository userRepository;
////	
////
////	
////	
////	@PostMapping("/create")
////	public String store(@Valid @ModelAttribute("nota") Note nota,
////			BindingResult bindingResult,
////			Model model) {
////		
////		if(bindingResult.hasErrors()) {
////			return "/notes/edit";
////		}
////		
////		noteRepository.save(nota);
////		nota.setNoteDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
////				
////		Authentication autenticazione = SecurityContextHolder.getContext().getAuthentication();
////		String username = autenticazione.getName();
////		
////	    Optional<User> user = userRepository.findByUsername( username);
////	    User utenteloggato = user.get();
////		
////		nota.setAuthor(utenteloggato);
////		noteRepository.save(nota);
////		return "redirect:/tickets/show/" + nota.getTicket().getId();
////	}
////	
////	@GetMapping("/edit/{id}")
////	public String edit(@PathVariable("id") Integer idNota, Model model) {
////		
////		Note nota = noteRepository.findById(idNota).get();
////		
////		model.addAttribute("nota", nota);
////        model.addAttribute("ticketId", idNota);
////		model.addAttribute("editMode", true);
////		
////		return "/notes/edit";
////	}
////	
////	@PostMapping("/edit/{id}")
////	public String modificaNota(@PathVariable("id") Integer id,
////			@Valid @ModelAttribute("nota") Note nota,
////			BindingResult bindingResult, Model model) {
////		
////		if(nota.getNote().isBlank()) {
////			bindingResult.addError(new ObjectError("nota", 
////					"Inserire nota"));
////		}
////		
////		if(bindingResult.hasErrors()) {
////			model.addAttribute("editMode", true);
////			
////			return "/notes/edit";
////		}
////		
////		nota.setNoteDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
////		
////		Authentication autenticazione = SecurityContextHolder.getContext().getAuthentication();
////		String username = autenticazione.getName();
////		
////	    Optional<User> user = userRepository.findByUsername( username);
////	    User utenteloggato = user.get();
////		
////		nota.setAuthor(utenteloggato);
////		noteRepository.save(nota);
////		
////		return "redirect:/tickets/show/" + nota.getTicket().getId();
//	}
//	
//	
//}
