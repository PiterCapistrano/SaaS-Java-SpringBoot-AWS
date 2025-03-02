package com.meusaas.usuarios.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.meusaas.usuarios.exception.EmailDuplicadoException;
import com.meusaas.usuarios.model.Usuario;
import com.meusaas.usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
  
  private final UsuarioRepository repository;

  public Page<Usuario> listarUsuariosPaginado(int pagina, int tamanho) {
    PageRequest pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());
    return repository.findAll(pageable);
  }

  public Usuario criarUsuario(Usuario user) {
    Usuario usuario = new Usuario(null, user.getNome(), user.getEmail(), user.getSenha(), "tenant-exemplo");
    if(repository.existsByEmail(user.getEmail())) {
      throw new EmailDuplicadoException("Já existe um usuário com esse e-mail: " + usuario.getEmail());
    } else { 
      return repository.save(usuario);
    }
  }
}
