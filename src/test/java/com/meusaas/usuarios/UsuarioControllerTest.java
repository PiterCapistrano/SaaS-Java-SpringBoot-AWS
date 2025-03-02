package com.meusaas.usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Test
  void deveRetornarListaVaziaInicial() throws Exception {
    mockMvc.perform(get("/usuarios"))
      .andExpect(status().isOk())
      .andExpect(content().json("[]"));
  }
  
}
