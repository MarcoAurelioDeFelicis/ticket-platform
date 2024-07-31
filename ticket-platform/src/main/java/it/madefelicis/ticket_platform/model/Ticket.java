package it.madefelicis.ticket_platform.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message= "Title cannot be null")
	@Column(name= "title", nullable= false)
	private String title;
	
	@NotBlank(message= "Body cannot be null")
	@Column(name= "body", nullable= false)
	private String body;
	
	@Column(nullable= false)
    private LocalDateTime ticket_date;
	
	@Column(nullable= false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        da_fare,in_corso,completato 
    }	
    
    @ManyToOne
    @JoinColumn(name= "user_id", nullable= false)
    @JsonBackReference
    private User user;
    
    @ManyToOne
    @JoinColumn(name= "category_id", nullable= false)
    @JsonBackReference
    private Category category;
    
    @OneToMany(mappedBy= "ticket", cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<Note> note;
    
//	GETTERS - SETTERS  //
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getTicket_date() {
		return ticket_date;
	}

	public void setTicket_date(LocalDateTime ticket_date) {
		this.ticket_date = ticket_date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}

    
	
	

}
