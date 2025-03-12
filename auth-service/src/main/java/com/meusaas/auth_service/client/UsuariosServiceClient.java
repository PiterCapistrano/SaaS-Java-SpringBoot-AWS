package com.meusaas.auth_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meusaas.auth_service.dto.UserDTO;

@FeignClient(name = "usuarios-service", url = "${usuarios-service.url}")
public interface UsuariosServiceClient {
  
  @GetMapping("/api/usuarios/by-email")
  UserDTO getUserByEmail(@RequestParam("email") String email);
}
