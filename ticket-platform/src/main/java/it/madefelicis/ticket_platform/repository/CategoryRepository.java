package it.madefelicis.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.ticket_platform.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findByCategoryIgnoreCase(String category);

}
