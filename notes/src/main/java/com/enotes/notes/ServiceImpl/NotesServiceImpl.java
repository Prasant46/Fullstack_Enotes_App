package com.enotes.notes.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enotes.notes.Entity.Notes;
import com.enotes.notes.Entity.User;
import com.enotes.notes.Repository.NotesRepo;
import com.enotes.notes.Service.NotesService;


@Service
public class NotesServiceImpl implements NotesService{


  
  @Autowired
  private NotesRepo notesRepo;

 

  @Override
  public Notes saveNotes(Notes notes) {

  
    
    return this.notesRepo.save(notes) ;
  }

  @Override
  public Notes getNotesById(int id) {
    Notes notes =this.notesRepo.findById(id).get();
  
    return notes;
  }

  @Override
  public Page<Notes> getAllNotesByUser(User user,int pageNo) {

    Pageable pageable = PageRequest.of(pageNo, 5);
    return this.notesRepo.findByUser(user, pageable);

  }

  @Override
  public Notes updateNotes(Notes notes) {
    return this.notesRepo.save(notes);

  }

  @Override
  public boolean deleteNotes(int id) {

    Notes notes =this.notesRepo.findById(id).get();

    if (notes!=null) {
      
      this.notesRepo.delete(notes);
      return true;
    }


    return false;
  }

  
} 