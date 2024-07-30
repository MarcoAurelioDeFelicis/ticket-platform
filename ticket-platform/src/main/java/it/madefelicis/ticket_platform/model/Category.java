package it.madefelicis.ticket_platform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "category")
public class Category {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank(message = "Category cannot be null")
	@Column(name= "category")
	private String category;
	
//	CATEGORIA COLLEGATA A TICKET MANY TO MANY
	@ManyToMany(mappedBy = "categories")
	private List<Ticket> ticket;
	
//	GETTERS - SETTERS  //

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return category;
	}

	public void setName(String category) {
		this.category = category;
	}
	
	

}
