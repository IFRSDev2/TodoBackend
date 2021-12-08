package com.ifrs.edu.Projetojavaback.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ifrs.edu.Projetojavaback.Dao.TarefaDAO;

import com.ifrs.edu.Projetojavaback.Entidades.Tarefa;
import com.ifrs.edu.Projetojavaback.erros.NaoEncontrado;
import com.ifrs.edu.Projetojavaback.erros.RequisicaoInvalida;

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
    public Iterable<Tarefa> listar() {
        return tarefaDao.findAll();
    }



///////////// INSERIR TAREFA ////////////////////////           
    
    @RequestMapping(path = "/tarefas/", method = RequestMethod.POST)
    public Tarefa inserir(@RequestBody Tarefa tarefa) {
        tarefa.setId(0);
        validaTarefa(tarefa);
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
    public Tarefa recuperar(@PathVariable int id) {
        Optional<Tarefa> findById = tarefaDao.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Tarefa inexistente");
        }
    }
    
///////////// ATUALIZAR TAREFA PELA ID ////////////////////////               

    @RequestMapping(path = "/tarefas/{id}", method = RequestMethod.PUT)
    public void atualizar(@PathVariable int id, @RequestBody Tarefa tarefa){
        if (tarefaDao.existsById(id)){
            tarefa.setId(id);
            validaTarefa(tarefa);
            tarefaDao.save(tarefa);
        }else{
            throw new NaoEncontrado("Tarefa inexistente");
        }
    }
    
///////////// APAGAR TAREFA PELA ID ////////////////////////               
    
    @RequestMapping(path = "/tarefas/{id}", method = RequestMethod.DELETE)
    public void apagar(@PathVariable int id){
        if (tarefaDao.existsById(id)){
        	tarefaDao.deleteById(id);
        }else {
            throw new NaoEncontrado("Tarefa inexistente");
        }
    } 
}