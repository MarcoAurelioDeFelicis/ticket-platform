package it.madefelicis.ticket_platform.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "note")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message= "Name cannot be null")
	@Column(name= "name")
	private String name;
	
	@Column(name= "note", nullable= false )
	private LocalDateTime noteDate;
	
	@ManyToOne
	@JoinColumn(name= "ticket_id" , nullable= false)
	@JsonBackReference
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name= "author_id" , nullable= false)
	@JsonBackReference
	private User author;
	
	
//	GETTERS - SETTERS  //
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(LocalDateTime noteDate) {
		this.noteDate = noteDate;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
