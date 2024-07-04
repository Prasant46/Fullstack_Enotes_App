package com.enotes.notes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.enotes.notes.Entity.User;



public interface UserRepo extends JpaRepository<User,Integer>{

  public boolean existsByEmail(String email);

  User findByEmail(String email);
  
}
