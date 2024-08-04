package it.madefelicis.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.madefelicis.platform.model.Note;


public interface NoteRepository extends JpaRepository<Note, Integer > {

}
