package com.enotes.notes.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String qualification;

  private String email;

  private String address;

  private String gender;

  private String password;

  private String role;

  @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", qualification=" + qualification + ", email=" + email
				+ ", address=" + address + ", gender=" + gender + ", password=" + password + ", role=" + role + "]";
	}


}
