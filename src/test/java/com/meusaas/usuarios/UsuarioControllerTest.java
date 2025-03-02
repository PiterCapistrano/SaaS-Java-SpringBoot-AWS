package com.meusaas.usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void deveCadastrarUsuario() throws Exception {
    String json ="""
        {
          "nome": "Novo Usuário",
          "email": "novo@email.com",
          "senha": "123456"
        }
        """;

        mockMvc.perform(post("/api/usuarios")
          .contentType(MediaType.APPLICATION_JSON)
          .content(json))
          .andExpect(status().isOk());
  }
  
  @Test
  void deveRetornarListaVaziaInicial() throws Exception {
    mockMvc.perform(get("/api/usuarios"))
      .andExpect(status().isOk())
      .andExpect(content().json("[]"));
  }
  
}
