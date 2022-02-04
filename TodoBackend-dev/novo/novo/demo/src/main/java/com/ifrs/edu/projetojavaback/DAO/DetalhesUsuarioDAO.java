package com.ifrs.edu.projetojavaback.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.ifrs.edu.projetojavaback.Data.DetalhesUsuario;
import com.ifrs.edu.projetojavaback.model.Usuario;

@Component
public class DetalhesUsuarioDAO implements UserDetailsService {

    private final UsuarioDAO repository;

    public DetalhesUsuarioDAO(UsuarioDAO repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByemailusuario(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }

        return new DetalhesUsuario(usuario);
    }

}