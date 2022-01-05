package com.ifrs.edu.projetojavaback.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.ifrs.edu.projetojavaback.model.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository <Usuario, Integer> {
    public List<Usuario> search( String Email);
}