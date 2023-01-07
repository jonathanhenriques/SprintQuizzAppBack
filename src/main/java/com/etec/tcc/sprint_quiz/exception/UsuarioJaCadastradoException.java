package com.etec.tcc.sprint_quiz.exception;


/**
 * Classe de Erro para a Excessão de usuário já cadastrado
 */
public class UsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioJaCadastradoException() {
		super("Usuário já cadastrado!");
	}

	public UsuarioJaCadastradoException(Object message) {
		super("Usuário já cadastrado! | " + message);
	}

	
}
