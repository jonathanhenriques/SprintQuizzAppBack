package com.etec.tcc.sprint_quiz.model;

import java.time.LocalDate;
import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a Questão no Banco
 * 
 * @author hsjon
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_questao")
public class Questao {

	// @EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull(message = "O atributo instituicao não pode ser nullo")
	private String instituicao;

	// @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
	@CreationTimestamp
//    @UpdateTimestamp
	private LocalDate ano;

	private String imagem;

	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
	private String texto;

	private DificuldadeQuestao dificuldade;

//	@OneToMany
//    @OneToMany(mappedBy = "questao", orphanRemoval = true)
//    @Cascade(CascadeType.REFRESH)
//    @OneToOne(cascade = CascadeType.REMOVE, optional = true)
//    @JoinColumn(name = "tb_alternativa_id")
//    @OneToMany(mappedBy = "questao")
//	@OneToMany
//	@JoinColumn(name = "alternativa_id")
//	@JoinColumn

	@OneToMany(fetch = FetchType.EAGER)
//	@OneToMany(orphanRemoval = true)
	@Cascade(CascadeType.MERGE)
	@JoinTable(name = "tb_questao_alternativas", joinColumns = @JoinColumn(name = "questao_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "alternativa_id", referencedColumnName = "id"))
//	@JsonIgnoreProperties(value = { "questao" }, allowSetters = true)
	private Set<Alternativa> alternativas;
//    private Alternativa alternativas;

	// @NotBlank(message = "O atributo resposta não pode ser nullo nem vazio!")
//    @Size(max = 1)
//    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "respostaid")
//    @Cascade(CascadeType.ALL)
//	@OneToOne(cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "resposta_id", referencedColumnName = "id")
//	@JsonProperty("respostaId")
	@OneToOne()
	private Alternativa resposta;

//    private String resposta;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
//    @JsonIgnoreProperties({"descritivo", "questoes"})
	@JsonIgnoreProperties(value = { "questoes" })
	private CategoriaQuestao categoria;

//	
	@ManyToOne
	@JoinColumn(name = "criador_id")
//    @JsonIgnoreProperties({"email", "senha", "foto", "tipo", "provas", "questoes"})
	@JsonIgnoreProperties(value = "questoes", allowSetters = true)
	private Usuario criador;


}
