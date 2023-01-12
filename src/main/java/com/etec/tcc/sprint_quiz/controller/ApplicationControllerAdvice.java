package com.etec.tcc.sprint_quiz.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ApiErrors;
import com.etec.tcc.sprint_quiz.exception.CargoJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.CargoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioJaCadastradoException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;

/**
 * Classe manipulador de erros que recebe os erros da aplicação
 * que foram declarados aqui e os modifica 
 * 
 * @author hsjon
 *
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(CategoriaProvaNotFoundException.class)
//	 @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCategoriaProvaNotFoundException(CategoriaProvaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()), 
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

//	Exception padrão OLD
//    @ExceptionHandler(CategoriaProvaNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrors handleCategoriaProvaNotFoundException(CategoriaProvaNotFoundException ex) {
//        String mensagemErro = ex.getMessage();
//        return new ApiErrors(mensagemErro);
//    }

	@ExceptionHandler(CategoriaQuestaoNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCategoriaQuestaoNotFoundException(CategoriaQuestaoNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ProvaNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleProvaNotFoundException(ProvaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(QuestaoNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleQuestaoNotFoundException(QuestaoNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(AlternativaNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleAlternativaNotFoundException(AlternativaNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UsuarioNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleUsuarioNotFoundException(UsuarioNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UsuarioJaCadastradoException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleUsuarioJaCadastradoException(UsuarioJaCadastradoException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.CONFLICT.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(CargoNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCargoNotFoundException(CargoNotFoundException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CargoJaCadastradoException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleCargoJaCadastradoException
	(CargoJaCadastradoException ex,
			HttpServletRequest request) {
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.CONFLICT.value(), Arrays.asList(ex.getMessage()),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiErrors> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		BindingResult bindingResult= ex.getBindingResult();
		ApiErrors error = new ApiErrors(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), bindingResult,
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
////	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//		BindingResult bindingResult= ex.getBindingResult();
////		List<ObjectError>lista = bindingResult.getAllErrors();
////		bindingResult.getAllErrors().forEach(error -> lista.add(error.getDefaultMessage()));
//		return new ApiErrors(bindingResult);
//	}

}
