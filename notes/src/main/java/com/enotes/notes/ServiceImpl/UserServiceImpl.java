package com.enotes.notes.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.enotes.notes.Entity.User;
import com.enotes.notes.Repository.UserRepo;
import com.enotes.notes.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public User saveUser(User user) {

    user.setRole("USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User newUser = this.userRepo.save(user);

    return newUser;
  }


  // public void removeSessionMessage(String email){
  //    RequestContextHolder
  // }

  public void removeSessionMessage() {
		@SuppressWarnings("null")
    HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}

  public void removeWarningSessionMessage() {
		@SuppressWarnings("null")
    HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("warning");
	}
  
}
