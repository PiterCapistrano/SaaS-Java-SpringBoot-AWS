package com.meusaas.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meusaas.auth_service.model.AuthenticationRequest;
import com.meusaas.auth_service.model.AuthenticationResponse;
import com.meusaas.auth_service.service.CustomUserDetailsService;
import com.meusaas.auth_service.service.JwtService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Usuário ou Senha Inválidas", e);
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
    String jwt = jwtService.generateToken(userDetails);
    
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
  
}
