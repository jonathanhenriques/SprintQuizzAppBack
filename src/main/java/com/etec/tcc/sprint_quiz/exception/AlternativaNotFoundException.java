package com.etec.tcc.sprint_quiz.exception;

public class AlternativaNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlternativaNotFoundException(Object message) {
        super("Alternativa não encontrada! | " + message);
    }

    public AlternativaNotFoundException() {
        super("Alternativa não encontrada!");
    }
}
