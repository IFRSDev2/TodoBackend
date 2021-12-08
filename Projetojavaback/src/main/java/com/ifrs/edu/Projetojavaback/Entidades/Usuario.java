package com.ifrs.edu.Projetojavaback.Entidades;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nomeUsuario;
    private String telefoneusuario;
    private String emailusuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
