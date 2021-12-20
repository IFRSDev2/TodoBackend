package com.projetojavaback.PROJETOJAVABACK.Entidades;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Tarefas {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeTarefa;
    private String Tipotarefa;
    private String LocalTarefa;

}
