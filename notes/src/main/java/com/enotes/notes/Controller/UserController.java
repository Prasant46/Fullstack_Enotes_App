package com.enotes.notes.Controller;



import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enotes.notes.Entity.Notes;
import com.enotes.notes.Entity.User;
import com.enotes.notes.Repository.NotesRepo;
import com.enotes.notes.Repository.UserRepo;
import com.enotes.notes.Service.NotesService;

import jakarta.servlet.http.HttpSession;





@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private NotesRepo notesRepo;

  @Autowired
  private NotesService notesService;

  @ModelAttribute
  public User getUser(Principal p, Model m){
    String email =p.getName();
    User user = this.userRepo.findByEmail(email);

    m.addAttribute("user" , user);

    return user;
   
  }

  @GetMapping("/")
  public String home(){

    return "index";
  }

  @GetMapping("/addNotes")
  public String addNotes(){
    
    return "add_notes";
  } 
  
  
  @GetMapping("/viewNotes")
  public String viewNotes(Model m ,Principal p, @RequestParam(defaultValue = "0") Integer pageNo){
    
    User user=getUser(p, m);

    Page<Notes> notes =notesService.getAllNotesByUser(user, pageNo);

    

    m.addAttribute("currentPage",pageNo);
    m.addAttribute("totalElements",notes.getTotalElements());
    m.addAttribute("totalPages", notes.getTotalPages());
    m.addAttribute("notesList", notes.getContent());
    return "view_notes";
  }
  
  @GetMapping("/editNotes/{id}")
  public String editNotes(@PathVariable int id, Model m){
    Notes note=notesRepo.findById(id).get();
    m.addAttribute("n",note);
    return "edit_notes";

  }

  @PostMapping("/saveNotes")
  public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m){

    String getTitle=notes.getTitle();
    String getDescription=notes.getDescription();

    if(getTitle.isEmpty() ||getDescription.isEmpty()){

      session.setAttribute("warning", "Title and Description Cannot be Empty");       
      return "redirect:/user/addNotes";  
     }



    notes.setDate(LocalDate.now());
    notes.setUser(getUser(p, m));
    Notes saveNotes= notesService.saveNotes(notes);

    if (saveNotes != null) {

      session.setAttribute("msg", "Notes Saved Successfully");        
    }else{
      session.setAttribute("msg", "Something went wrong on Server");
    }

    return "redirect:/user/addNotes";

  }


  @PostMapping("/updateNotes")
  public String updatedNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m){

    String getTitle=notes.getTitle();
    String getDescription=notes.getDescription();
    int id=notes.getId();

    if(getTitle.isEmpty() ||getDescription.isEmpty()){

      session.setAttribute("warning", "Title and Description Cannot be Empty");       
      return "redirect:/user/editNotes/"+id;  
     }




    notes.setDate(LocalDate.now());
    notes.setUser(getUser(p, m));

    Notes saveNotes= notesService.saveNotes(notes);

    if (saveNotes != null) {

      session.setAttribute("msg", "Notes Updated Successfully");        
    }else{
      session.setAttribute("msg", "Something went wrong on Server");
    }

    return "redirect:/user/viewNotes";

  }

  @GetMapping("/deleteNotes/{id}")
  public String deleteNotes(@PathVariable int id,HttpSession session,Model m){


    Notes note = this.notesRepo.findById(id).get();

    Boolean f = this.notesService.deleteNotes(id);
    m.addAttribute("n",note);

    if (f) {

      session.setAttribute("msg", "Notes Deleted Successfully");        
    }else{
      session.setAttribute("msg", "Something went wrong on Server");
    }

    return "redirect:/user/viewNotes";

  }
  
}
