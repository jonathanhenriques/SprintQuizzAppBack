package com.etec.tcc.sprint_quiz.service;

import java.util.List;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;

/**
 * 
 * @author Jonathan Henrique
 * @date 2022
 */
public interface AlternativaService {

	/**
	 * Retorna todas as Alternativas
	 * 
	 * @return <code>List<Alternativa></code> Alternativa
	 */
	List<AlternativaDTO> getAll();

	/**
	 * Método que busca uma Alternativa pelo id.
	 * 
	 * @param id
	 * @return objeto <code>AlternativaDTO</code>
	 */
	AlternativaDTO getById(Long id);

	/**
	 * Retorna nenhuma,uma ou mais Alternativas filtradas por um Texto.
	 * 
	 * @param texto
	 * @return <code>List<Alternativa></code> Alternativa
	 */
	List<AlternativaDTO> getAllByTexto(String texto);

	/**
	 * Método que salva uma Alternativa
	 * 
	 * @param alternativa
	 * @return objeto <code>AlternativaDTO</code> salvo.
	 */
	AlternativaDTO post(AlternativaDTO alternativa);

	/**
	 * Método que atualiza uma Alternativa
	 * 
	 * @param alternativa
	 * @return objeto <code>AlternativaDTO</code> atualizado.
	 */
	AlternativaDTO put(AlternativaDTO alternativa);

	/**
	 * Método que remove uma Alternativa da questao
	 * 
	 * 
	 *  verifica se a Questao existe
	 *  
	 *  verifica se a alternativa existe
	 *  
	 *  se a alternativa for a resposta da questao
	 *  o atributo resposta da questao recebe null
	 *  
	 *  remove a alternativa da lista da questao
	 *  
	 *  deleta a alternativa
	 * 
	 * @param Long
	 * @param Long
	 */
	void removeAlternativaDeQuestao(Long questaoId, Long alternativaId);

	/**
	 * Método que deleta uma Alternativa
	 * 
	 * @param id
	 */
	void deleteById(Long id);

	
	/**
	 * Método que salva uma lista de Alternativas
	 * 
	 * @param List<Alternativa> alternativasDTO
	 */
	List<AlternativaDTO> postListaAlternativa(List<AlternativaDTO> alternativasDTO);

	
}
