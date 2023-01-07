package com.etec.tcc.sprint_quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * classe representando os usuarios no Banco
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // permite construir obj com mais facilidade
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "usuario_id")
	private Long id;

//	@NotBlank(message = "O atributo nome não pode ser nullo nem vazio!")
	@NotBlank(message = "nome {campo.texto.notBlank.obrigatorio}")
	@Size(min = 3, max = 75, message = "nome {campo.texto.sizeMin} 3")
	private String nome;

// necessario na antiga implementacao de security pois login é feito com email
//    @Schema(example = "email@email.com.br")
//    @NotBlank(message = "O atributo email não pode ser nullo nem vazio")
//    @Email(message = "Deve ser um email válido (email@email.com)")
//    private String usuario;

	@Schema(example = "email@email.com.br")
	@NotBlank(message = "email {campo.texto.notBlank.obrigatori}")
	@Column(unique = true)
	@Email(message = "{campo.email}")
	private String username; // campo de login

	@NotBlank(message = "senha {campo.texto.notBlank.obrigatorio}")
	@Size(min = 8, message = "senha {campo.texto.sizeMin} 8")
	private String password;

//    @Schema(name = "link da foto")
	private String foto;

//	private boolean enable;

//	@Schema(example = "admin / user")
//	private String tipo;
//	@ManyToMany //declaracao gera uma nova tabela para associar usuario com a role
//	@JoinTable(name = "TB_USUARIO_ROLE", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private List<RolesModel> roles;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//	@ManyToMany
	@Pattern(regexp="^(ROLE_ADMIN|ROLE_USER)$",message="{campo.padroes.role}")
	private Collection<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "criador")@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "criador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
//	@JsonIgnoreProperties({ "instituicao", "ano", "texto", "opcao_1", "opcao_2", "opcao_3", "opcao_4", "opcao_5",
//			"resposta", "categoria", "criador" })
	private List<Questao> questoes = new ArrayList();

	@OneToMany(mappedBy = "usuario")@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
//	@JsonIgnoreProperties(value = { "questoes", "descricao", "duracao", "usuario", "instituicao",
//			"categoria" }, allowSetters = true)
	private List<Prova> provas = new ArrayList();
	
	
//	public UserPrincipal(User user) {
//	    this.username = user.getUsername();
//	    this.password = user.getPassword();
//
//	    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//	    // ROLE_ADMIN , ROLE_USER ADMIN, USER ....
//	    authorities = user.getRoles().stream().map(role -> {
//	      return new SimpleGrantedAuthority(role.getName());
//	    }).collect(Collectors.toList());
//
//	    this.authorities = authorities;
//
//	  }
	
	
	public boolean isRolesAdmin() {
		return this.roles.stream().anyMatch(r -> r.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
	}

}