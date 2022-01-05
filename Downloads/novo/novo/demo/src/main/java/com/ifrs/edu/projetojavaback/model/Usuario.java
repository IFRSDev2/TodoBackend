package com.ifrs.edu.projetojavaback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable {
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioid;
	@NotNull
    private String nomeUsuario;

    private String telefoneusuario;
	@NotNull
    private String emailusuario;
	@NotNull
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return usuarioid;
	}
	public void setId(int id) {
		this.usuarioid = usuarioid;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTelefoneusuario() {
		return telefoneusuario;
	}
	public void setTelefoneusuario(String telefoneusuario) {
		this.telefoneusuario = telefoneusuario;
	}
	public String getEmailusuario() {
		return emailusuario;
	}
	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}

}
