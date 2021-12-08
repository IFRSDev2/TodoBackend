package com.ifrs.edu.Projetojavaback.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ifrs.edu.Projetojavaback.Entidades.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository <Usuario, Integer> {
    
}