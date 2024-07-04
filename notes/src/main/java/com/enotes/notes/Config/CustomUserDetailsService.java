package com.enotes.notes.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enotes.notes.Entity.User;
import com.enotes.notes.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
  //   User user = this.userRepo.findByEmail(username);

  //   if (user == null) {
  //     throw new UsernameNotFoundException("User not found");
  //   }else{
  //     return new CustomUser(user);
  //   }

    User user = this.userRepo.findByEmail(username);

    if (user != null) {

        

        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).roles(user.getRole())
        .build();
        
    }else{
        throw new UsernameNotFoundException(username);
    }

    
  }

  


    
  

  

  
}
