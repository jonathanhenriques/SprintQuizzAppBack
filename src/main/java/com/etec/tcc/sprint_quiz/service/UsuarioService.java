package com.etec.tcc.sprint_quiz.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;

public interface UsuarioService{


	 Optional<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario);
	 
	 Optional<Role> cadastrarRole(Role role); //verificar se devo passar DTO ou a classe 
	 
	 void addRoleUsuario(String username, String cargo);
	 

}
