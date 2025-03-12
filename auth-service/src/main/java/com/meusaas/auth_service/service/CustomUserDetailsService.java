package com.meusaas.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meusaas.auth_service.client.UsuariosServiceClient;
import com.meusaas.auth_service.dto.UserDTO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuariosServiceClient usuariosServiceClient;

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        // Chama o micro serviço usuarios-service para obter os dados do usuário
        UserDTO user = usuariosServiceClient.getUserByEmail(useremail);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + useremail);
        }
         // Retorne um objeto User do Spring Security; aqui, as roles estão sendo ignoradas para simplificar
        return User.withUsername(user.getEmail()).password(user.getPassword()).authorities(user.getRoles()).build();
    }
}
