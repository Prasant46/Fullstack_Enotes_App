package com.enotes.notes.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.notes.Entity.Notes;
import com.enotes.notes.Entity.User;

public interface NotesRepo extends JpaRepository<Notes,Integer>{


  Page<Notes>  findByUser(User user,Pageable pageable);
  
} 
