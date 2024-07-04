package com.enotes.notes.Config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {

        boolean isUser = authentication.getAuthorities().stream().anyMatch(granted->granted.getAuthority().equals("ROLE_USER"));

        if (isUser) {

          setDefaultTargetUrl("/user/addNotes");          
        }else{

          throw new UsernameNotFoundException("User not found or Something wrong on server");
        }




        super.onAuthenticationSuccess(request, response, authentication);
  }

  
}
