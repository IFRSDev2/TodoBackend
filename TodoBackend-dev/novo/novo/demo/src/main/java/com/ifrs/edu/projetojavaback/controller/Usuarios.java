package com.ifrs.edu.projetojavaback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.ifrs.edu.projetojavaback.DAO.UsuarioDAO;
import com.ifrs.edu.projetojavaback.erros.NaoEncontrado;
import com.ifrs.edu.projetojavaback.erros.RequisicaoInvalida;
import com.ifrs.edu.projetojavaback.model.Usuario;

@RestController
@RequestMapping(path = "/api")
public class Usuarios {
    public UsuarioDAO repository;
    @Autowired
    UsuarioDAO usuarioDao;
    private final PasswordEncoder encoder;
    public Usuarios(UsuarioDAO repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
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

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<Usuario> optUsuario = repository.findByemailusuario(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getSenha());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
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
        usuario.setSenha(encoder.encode(usuario.getSenha()));
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

//////////ATUALIZAR USUÁRIO PELA ID //////////////

    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.PUT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario){
        if (usuarioDao.existsById(id)){
            usuario.setId(id);
            validaUsuario(usuario);
            usuarioDao.save(usuario);
        }else{
            throw new NaoEncontrado("Usuário inexistente");
        }
    }

///////////// APAGAR USUÁRIO PELA ID ////////////////////////

    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.DELETE)
    public void apagar(@PathVariable int id){
        if (usuarioDao.existsById(id)){
            usuarioDao.deleteById(id);
        }else {
            throw new NaoEncontrado("Usuário inexistente");
        }

    }


}