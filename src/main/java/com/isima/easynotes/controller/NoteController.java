package com.isima.easynotes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isima.easynotes.note.Note;
import com.isima.easynotes.repository.NoteRepository;



@RestController
public class NoteController {
	@Autowired
    private final NoteRepository noteRepository;
	
	
	 public NoteController(NoteRepository noteRepository) {
		super();
		this.noteRepository = noteRepository;
	}

	@GetMapping("/Notes")
	    public List<Note> getAllNotes() {
	        return noteRepository.findAll();
	    }

	 @GetMapping("/Notes/{id}")
	    public Optional<Note> findNoteById(@PathVariable long id)  {
	        return noteRepository.findById(id);
	    }
	 
	 @PostMapping("/Notes")
	 public void ajouterNote(@RequestBody Note note) {
		 noteRepository.save(note); 
	 }

	 @PutMapping("/Notes")
	 public void modifierNote(@RequestBody Note note) {
		 noteRepository.save(note); 
	 }
	 
	 @DeleteMapping(value="/Notes/{id}")
	 public void supprimerNote(@PathVariable long id) {
		 noteRepository.deleteById(id);
	 }
	 
	 @GetMapping(value="/Notess/{title}")
	 public List<Note> findNotesByTitle(@PathVariable String title) {
	        return noteRepository.findByTitleContaining(title);
	    }
	 
	 @GetMapping(value="/NotesC/{c}")
	 public List<Note> findNotesByContent(@PathVariable String c) {
	        return noteRepository.findByContentContaining(c);
	    }

	 
	 @GetMapping(value="/tri")
	 public List<Note> findAllByOrderByCreatedAtDesc() {
	        return noteRepository.findAllByOrderByCreatedAtDesc();
	    }
	 
	 @GetMapping(value="/Note/{date}")
	 public List<Note> findByCreatedAtBefore(LocalDateTime date) {
	        return noteRepository.findByCreatedAtBefore(date);
	    }
	 
	 @GetMapping(value="/NoteAfter/{days}")
	  public List<Note> getRecentlyUpdatedNotes(@PathVariable int days) {
	        LocalDateTime referenceDate = LocalDateTime.now().minusDays(days);
	        return noteRepository.findByUpdatedAtAfter(referenceDate);
	    }
	
}
