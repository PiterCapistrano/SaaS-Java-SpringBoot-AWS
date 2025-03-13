package com.meusaas.usuarios.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.meusaas.usuarios.dto.UsuarioDTO;
import com.meusaas.usuarios.model.Usuario;
import com.meusaas.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
  
  private final UsuarioService service;

  @GetMapping
  public ResponseEntity<Page<Usuario>> listarUsuariosPaginado(
      @RequestParam(defaultValue = "0") Integer pagina,
      @RequestParam(defaultValue = "10") Integer tamanho) {
        Page<Usuario> usuarios = service.listarUsuariosPaginado(pagina, tamanho);
    return ResponseEntity.ok(usuarios);
  }

  @Operation(summary = "Criar um novo usuário")
  @PostMapping("/cadastro")
  public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
    Usuario usuario = toEntity(usuarioDTO);
    return ResponseEntity.ok(service.criarUsuario(usuario));
  }

  public Usuario toEntity(UsuarioDTO dto) {
    Usuario usuario = new Usuario();
    usuario.setNome(dto.getNome());
    usuario.setEmail(dto.getEmail());
    usuario.setSenha(dto.getSenha());
    return usuario;
}

}
