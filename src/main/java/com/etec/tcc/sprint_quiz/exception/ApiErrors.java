package com.etec.tcc.sprint_quiz.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * classe standart que monta o obj de erro a ser exibido
 * 
 * @author hsjon
 *
 */
@AllArgsConstructor
@Getter
@Hidden
public class ApiErrors {

	private LocalDateTime timestamp;
	private Integer status;
    @Getter
	private List<String> errors;
//    private String errors;
	private String path;
	
	
	

//	public ApiErrors(BindingResult bindingResult) {
//		this.errors = new ArrayList<>();
//		bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
//	}




	public ApiErrors(LocalDateTime timestamp, Integer status, BindingResult bindingResult, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.errors = new ArrayList<>();
		bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
		this.path = path;
	}


}
