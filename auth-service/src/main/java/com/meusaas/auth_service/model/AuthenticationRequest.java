package com.meusaas.auth_service.model;

public class AuthenticationRequest {
  
  private String email;
  private String password;

  public AuthenticationRequest() {
  }

  public AuthenticationRequest(String email, String password) {
    this.email = email;
    this.password = password;
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
}
