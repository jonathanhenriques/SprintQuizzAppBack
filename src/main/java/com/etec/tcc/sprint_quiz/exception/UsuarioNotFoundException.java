package com.etec.tcc.sprint_quiz.exception;

public class UsuarioNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(Object message) {
        super("Usuário não encontrado | " + message);
    }

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
