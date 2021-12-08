package com.ifrs.edu.Projetojavaback.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ifrs.edu.Projetojavaback.Entidades.Usuario;
import com.ifrs.edu.Projetojavaback.Entidades.Tarefas;
@Repository
public interface UsuarioDAO extends CrudRepository <Usuario, Integer> {

}