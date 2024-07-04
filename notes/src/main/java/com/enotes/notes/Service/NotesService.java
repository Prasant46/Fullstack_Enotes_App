package com.enotes.notes.Service;



import org.springframework.data.domain.Page;

import com.enotes.notes.Entity.Notes;
import com.enotes.notes.Entity.User;

public interface NotesService {

  public Notes saveNotes(Notes notes);

  public Notes getNotesById( int id);

  public Page<Notes> getAllNotesByUser(User user,int pageNo);

  public Notes updateNotes(Notes notes);

  public boolean deleteNotes(int id);

  
}
