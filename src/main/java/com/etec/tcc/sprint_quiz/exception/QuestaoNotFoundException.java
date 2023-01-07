package com.etec.tcc.sprint_quiz.exception;

public class QuestaoNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestaoNotFoundException(Object message) {
        super("Questão não encontrada | " + message);
    }

    public QuestaoNotFoundException() {
        super("Questão não encontrada!");
    }
}
