package com.meusaas.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meusaas.usuarios.dto.UsuarioDTO;
import com.meusaas.usuarios.model.Usuario;
import com.meusaas.usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  
  private final UsuarioRepository repository;

  public List<Usuario> listar() {
    return repository.findAll();
  }

  public Usuario criar(UsuarioDTO dto) {
    Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), "tenant-exemplo");
    return repository.save(usuario);
  }
}
