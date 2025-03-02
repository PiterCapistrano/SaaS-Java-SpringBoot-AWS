package com.meusaas.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meusaas.usuarios.dto.UsuarioDTO;
import com.meusaas.usuarios.model.Usuario;
import com.meusaas.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
  
  private final UsuarioService service;

  @GetMapping
  public List<Usuario> listar() {
    return service.listar();
  }

  @PostMapping
  public Usuario criar(@RequestBody @Valid UsuarioDTO dto) {
    return service.criar(dto);
  }
}
