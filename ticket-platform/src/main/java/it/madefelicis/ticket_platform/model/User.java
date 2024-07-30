package it.madefelicis.ticket_platform.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "user")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name= "name", nullable= false)
	private String name;
	
	@NotBlank(message = "Username cannot be null")
	@Column(name= "username", nullable=false, unique=true, length= 50)
	private String username;
	
	@NotBlank(message= "Password cannot be null")
	@Size(min= 5, max= 12, message= "Password must have 5 or 12 chars... SPERNACCHIATA" )
	@Column(name= "password", nullable=false, unique =false)
	private String password;
	
	@Column(name= "status", nullable=false, columnDefinition= "TINYINT(1")
	private boolean status;
	// 0 = OCCUPATO
	// 1 = DISPONIBILE
	
	@OneToMany(mappedBy= "user", cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<Ticket> ticket;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JsonBackReference
	private Set<Role> role;
	
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	

	
	
	
	

}
