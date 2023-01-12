package com.etec.tcc.sprint_quiz.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class RoleDTO {
	
private Long id;
	
	
	@NotBlank(message = "cargo {campo.texto.notBlank.obrigatorio}")
	private final String cargo;
	
	@NotBlank(message = "username {campo.texto.notBlank.obrigatorio}")
	private final String username;

}
