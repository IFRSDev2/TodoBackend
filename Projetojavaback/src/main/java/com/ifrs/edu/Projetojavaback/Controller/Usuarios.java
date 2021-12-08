package com.ifrs.edu.Projetojavaback.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ifrs.edu.Projetojavaback.Dao.UsuarioDAO;
import com.ifrs.edu.Projetojavaback.Entidades.Usuario;
import com.ifrs.edu.Projetojavaback.erros.NaoEncontrado;
import com.ifrs.edu.Projetojavaback.erros.RequisicaoInvalida;


@RestController
@RequestMapping(path = "/api")
public class Usuarios {

    @Autowired
    UsuarioDAO usuarioDao;


/////// VALIDAÇão DO CADASTRO DO USUÁRIO//////////

    public void validaUsuario(Usuario usuario){
        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isEmpty()){
            throw new RequisicaoInvalida("Usuário tem que ter nome");
        }
             if (usuario.getTelefoneusuario()== null || usuario.getTelefoneusuario().isEmpty()){
            throw new RequisicaoInvalida("Usuário tem que ter telefone");
        }
        if (usuario.getEmailusuario() == null || usuario.getEmailusuario().isEmpty()){
            throw new RequisicaoInvalida("Usuário tem que ter e-mail");
        }
    }
