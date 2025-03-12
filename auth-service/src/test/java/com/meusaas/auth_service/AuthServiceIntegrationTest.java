package com.meusaas.auth_service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.meusaas.auth_service.client.UsuariosServiceClient;

import static org.mockito.Mockito.when;

import com.meusaas.auth_service.dto.UserDTO;

@SpringBootTest(classes = AuthServiceApplication.class)
@AutoConfigureMockMvc
public class AuthServiceIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  //Mocka o Feign Client que chama o usuário-service
  @MockBean
  private UsuariosServiceClient usuariosServiceClient;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testAuthentication_success() throws Exception {
    // Simula um usuário retornado pelo usuário-service com senha criptografada
    String rawPassword = "password123";
    String encodedPassword = passwordEncoder.encode(rawPassword);

    UserDTO fakeUser = new UserDTO("test@example.com", encodedPassword, List.of("ROLE_USER"));

    when(usuariosServiceClient.getUserByEmail("test@example.com")).thenReturn(fakeUser);

    // Cria o payload da requisição de login (JSON)
    String requestBody = "{\"email\": \"test@example.com\", \"password\": \"password123\"}";

    // Envia a requisição e verifica se o token JWT é retornado
    mockMvc.perform(post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.jwt").exists());
  }
}