package com.ifrs.edu.projetojavaback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ifrs.edu.projetojavaback.DAO.TarefaDAO;
import com.ifrs.edu.projetojavaback.erros.NaoEncontrado;
import com.ifrs.edu.projetojavaback.erros.RequisicaoInvalida;
import com.ifrs.edu.projetojavaback.model.Tarefa;

@RestController

@RequestMapping(path = "/api")

public class Tarefas {

    @Autowired
    TarefaDAO tarefaDao;


///////////// VALIDAÇÕES ////////////////////////

    public void validaTarefa(Tarefa tarefa){
        if (tarefa.getNomeTarefa() == null || tarefa.getNomeTarefa().isEmpty()){
            throw new RequisicaoInvalida("Tarefa tem que ter nome");
        }

        if (tarefa.getTipotarefa()== null || tarefa.getTipotarefa().isEmpty()){
            throw new RequisicaoInvalida("Tarefa tem que ter um tipo");
        }
        if (tarefa.getLocalTarefa() == null || tarefa.getLocalTarefa().isEmpty()){
            throw new RequisicaoInvalida("Tarefa tem que ter um local");
        }
    }

///////////// LISTAR TAREFAS ////////////////////////

    @RequestMapping(path = "/tarefas/", method = RequestMethod.GET)
    public void ListarTarefas(@RequestHeader int size ,@RequestHeader int page  ){
        ListarTarefas(size,page);
    }
    public ResponseEntity ListarTarefas(
         @PageableDefault(sort = "name",
                 direction = Sort.Direction.ASC
                 
                 ) Pageable page){

      return ResponseEntity.ok(tarefaDao.findAll(page));
  }

///////////// INSERIR TAREFA ////////////////////////

    @RequestMapping(path = "/tarefas/", method = RequestMethod.POST)
    public Tarefa inserir(@RequestBody Tarefa tarefa) {
        validaTarefa(tarefa);
        tarefa.setTarefaid(Long.valueOf(0));

        Iterable<Tarefa> tarefas = tarefaDao.findAll();
        boolean nomeIgual = false;
        for (Tarefa nomeEntrada : tarefas) {
            if (nomeEntrada.getNomeTarefa().equals(tarefa.getNomeTarefa())) {
                nomeIgual = true;
                break;
            }
        }
        if (nomeIgual== true) {
            throw new RequisicaoInvalida("Esta tarefa já foi cadastrada");

        }
        return tarefaDao.save(tarefa);
    }

///////////// RECUPERAR TAREFA PELA ID ////////////////////////

    @RequestMapping(path = "/tarefas/{id}", method = RequestMethod.GET)
    public Tarefa recuperar(@PathVariable Long id) {
        Optional<Tarefa> findById = tarefaDao.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Tarefa inexistente");
        }
    }

///////////// ATUALIZAR TAREFA PELA ID ////////////////////////

    @RequestMapping(path = "/tarefas/{id}", method = RequestMethod.PUT)
    public void atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa){
        if (tarefaDao.existsById(id)){
            tarefa.setTarefaid(id);
    
            validaTarefa(tarefa);
            tarefaDao.save(tarefa);
        }else{
            throw new NaoEncontrado("Tarefa inexistente");
        }
    }

///////////// APAGAR TAREFA PELA ID ////////////////////////

    @RequestMapping(path = "/tarefas/{id}", method = RequestMethod.DELETE)
    public void apagar(@PathVariable Long id){
        if (tarefaDao.existsById(id)){
            tarefaDao.deleteById(id);
        }else {
            throw new NaoEncontrado("Tarefa inexistente");
        }
    }
}