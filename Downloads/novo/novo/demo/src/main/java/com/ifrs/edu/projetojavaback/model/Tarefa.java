package com.ifrs.edu.projetojavaback.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tarefa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarefaid;
	@NotNull
	private String nomeTarefa;
	@NotNull
    private String Tipotarefa;
    private String LocalTarefa;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "usuarioid")
	private Usuario usuario;
	public Long getTarefaid() {
		return tarefaid;
	}
	public void setTarefaid(Long tarefaid) {
		this.tarefaid = tarefaid;
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
