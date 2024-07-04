package com.enotes.notes.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  

  @Autowired
  private UserDetailsService userDetailsService;

  
  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  

  @Bean
  public UserDetailsService userDetailsService(){

    return userDetailsService;
  }



  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());

    return provider;
  }
  
  

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    http.csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth->auth
        
        .requestMatchers("/user/**")
        .hasRole("USER")
        .requestMatchers("/**")
        .permitAll()
        .anyRequest().authenticated())

        .formLogin(form->form
          .loginPage("/signin")
          .loginProcessingUrl("/userLogin")
          .successHandler(new SuccessHandler())
          
          .permitAll());

       return http.build();
  

    
		
	

  }
}
