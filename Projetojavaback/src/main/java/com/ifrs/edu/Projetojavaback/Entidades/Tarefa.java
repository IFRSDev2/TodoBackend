package com.ifrs.edu.Projetojavaback.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomeTarefa;
	private String Tipotarefa;
	private String LocalTarefa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeTarefa() {
		return nomeTarefa;
	}

	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}

	public String getTipotarefa() {
		return Tipotarefa;
	}

	public void setTipotarefa(String tipotarefa) {
		Tipotarefa = tipotarefa;
	}

	public String getLocalTarefa() {
		return LocalTarefa;
	}

	public void setLocalTarefa(String localTarefa) {
		LocalTarefa = localTarefa;
	}

}
