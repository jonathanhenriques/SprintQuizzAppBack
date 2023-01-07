package com.etec.tcc.sprint_quiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que represente os tipos de roles(cargos, ex: Admin, user...)
 * 
 * @since 11/12/2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ROLE")
public class Role implements GrantedAuthority{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "role_id")
	private Long id;
	
	//atributo implementado com enums
//	@Enumerated(EnumType.STRING) //define que no lugar do enum, vai ser salvo uma string equivalente no banco
//	@Column(nullable = false, unique = true)
//	private RoleTipo roleTipo;
	
	@NotBlank(message = "cargo {campo.texto.notBlank.obrigatorio}")
	private String cargo;
	
	@Override
	public String getAuthority() {
		return this.cargo.toString();
	}

	public Role(@NotBlank(message = "cargo {campo.texto.notBlank.obrigatorio}") String cargo) {
		super();
		this.cargo = cargo;
	}
	
	
	

}

















