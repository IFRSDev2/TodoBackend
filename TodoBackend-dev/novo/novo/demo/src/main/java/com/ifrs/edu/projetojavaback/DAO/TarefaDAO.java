package com.ifrs.edu.projetojavaback.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifrs.edu.projetojavaback.model.Tarefa;

@Repository
public interface TarefaDAO extends JpaRepository <Tarefa, Long> {

}
