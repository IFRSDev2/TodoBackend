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
import com.ifrs.edu.Projetojavaback.Dao.UsuarioDAO;

@RestController
@RequestMapping(path = "/api")
public class Usuarios {

    @Autowired
    UsuarioDAO usuarioDao;


/////// VALIDAÇão DO CADASTRO DO USUÁRIO//////////

    public void validaUsuario(Usuario usuario) {
        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isEmpty()) {
            throw new RequisicaoInvalida("Usuário tem que ter nome");
        }
        if (usuario.getTelefoneusuario() == null || usuario.getTelefoneusuario().isEmpty()) {
            throw new RequisicaoInvalida("Usuário tem que ter telefone");
        }
        if (usuario.getEmailusuario() == null || usuario.getEmailusuario().isEmpty()) {
            throw new RequisicaoInvalida("Usuário tem que ter e-mail");
        }
    }


    ///////////// LISTAR USUÁRIOS ////////////////////////

    @RequestMapping(path = "/usuarios/", method = RequestMethod.GET)
    public Iterable<Usuario> listar() {
        return usuarioDao.findAll();
    }



    ///// FAz a inserção do do usuário e verifica se o usuário já foi cadastrado ///

    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    public Usuario inserir(@RequestBody Usuario usuario) {
        usuario.setId(0);
        validaUsuario(usuario);
        Iterable<Usuario> usuarios = usuarioDao.findAll();
        boolean nomeIgual = false;
        for (Usuario nomeEntrada : usuarios) {
            if (nomeEntrada.getNomeUsuario().equals(usuario.getNomeUsuario())) {
                nomeIgual = true;
                break;
            }
        }
        if (nomeIgual == true) {
            throw new RequisicaoInvalida("Este usuário já foi cadastrado");

        }
        return usuarioDao.save(usuario);
    }

///////// RECUPERAR USUÁRIO PELA ID ////////////

    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario recuperar(@PathVariable int id) {
        Optional<Usuario> findById = usuarioDao.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Usuário inexistente");
        }
    }





}