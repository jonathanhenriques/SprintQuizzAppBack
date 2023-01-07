package com.etec.tcc.sprint_quiz.exception;

public class CargoJaCadastradoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CargoJaCadastradoException() {
		super("Cargo não cadastrado!");
	}

	public CargoJaCadastradoException(Object message) {
		super("Cargo já cadastrado! |" + message);
	}

}
