package com.meusaas.auth_service.dto;

import java.util.List;

public class UserDTO {
  
  private String email;
  private String password;
  private List<String> roles;

  public UserDTO() {
  }

  public UserDTO(String email, String password, List<String> roles) {
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
