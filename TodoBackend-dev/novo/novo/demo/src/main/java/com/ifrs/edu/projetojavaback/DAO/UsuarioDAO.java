package com.ifrs.edu.projetojavaback.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;

import com.ifrs.edu.projetojavaback.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository <Usuario, Integer> {
  
    public Optional<Usuario> findByemailusuario(String emailusuario);

}