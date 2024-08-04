package it.madefelicis.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.platform.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

