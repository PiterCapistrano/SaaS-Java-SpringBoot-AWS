package com.meusaas.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.meusaas.auth_service.dto.UserDTO;

@FeignClient(name = "usuarios-service", url = "http://localhost:8081")
public interface UsuariosServiceClient {
  
  @GetMapping("/api/usuarios/{email}")
  UserDTO getUserByEmail(@PathVariable("email") String email);
}
