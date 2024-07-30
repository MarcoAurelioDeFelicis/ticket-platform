package it.madefelicis.ticket_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "category")
public class Category {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank(message = "Name cannot be null")
	@Column(name= "name")
	private String name;
	
	
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
	
	

}
