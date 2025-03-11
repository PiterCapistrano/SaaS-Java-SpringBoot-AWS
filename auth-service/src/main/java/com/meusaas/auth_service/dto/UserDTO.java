package com.meusaas.auth_service.dto;

public class UserDTO {
  
  private String email;
  private String password;
  private String roles;

  public UserDTO() {
  }

  public UserDTO(String email, String password, String roles) {
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public String getemail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setemail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }
}
