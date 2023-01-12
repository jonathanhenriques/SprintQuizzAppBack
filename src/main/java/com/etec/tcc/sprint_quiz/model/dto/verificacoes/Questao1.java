package com.etec.tcc.sprint_quiz.model.dto.verificacoes;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questao1 {

	private Long id;
	private String instituicao;
	private LocalDate ano;
	private String imagem;
	@NotBlank(message = "texto {campo.texto.notBlank.obrigatorio}")
	@Size(min = 1, max = 1000, message = "texto {campo.texto.sizeMax} 1000")
	private String texto;
	private DificuldadeQuestao dificuldade;
	private Set<Alternativa> alternativas;
	private Alternativa resposta;
	private CategoriaQuestao categoria;
	private Usuario criador;

}
