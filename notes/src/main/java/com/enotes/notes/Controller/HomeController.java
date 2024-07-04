package com.enotes.notes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.notes.Entity.User;
import com.enotes.notes.Repository.UserRepo;
import com.enotes.notes.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepo userRepo;
  
  @GetMapping("/")
  public String home(){

    return "index";
  }
  
  @GetMapping("/register")
  public String register(){
    
    return "register";
  }
  
  @PostMapping("/saveUser")
  public String saveUser(@ModelAttribute User user, HttpSession session){
    
    boolean f =this.userRepo.existsByEmail(user.getEmail());
    
    if (f) {
      session.setAttribute("msg", "Email already exist");
      
    }else{
      
      User saveUser= userService.saveUser(user);

      if (saveUser!= null) {

        session.setAttribute("msg", "Registration successfull");        
      }else{
        
        session.setAttribute("msg", "Something went wrong !! Register again");

        return "redirect:/register";
      }
    }


    
    return "redirect:/signin";
  }
  
  @GetMapping("/signin")
  public String login(){
    
    return "login";
  }



  
  
 
}
