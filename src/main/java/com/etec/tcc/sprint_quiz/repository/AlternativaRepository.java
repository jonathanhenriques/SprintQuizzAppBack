package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface que implementa a AlternativaRepository, com métodos JPA CRUD e
 * outras pesquisas personalizadas.
 * 
 * @author Jonathan Henrique
 * @date 28/11/2022
 */
@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

	/**
	 * Método que busca por todas as Alternativas por texto
	 * 
	 * @author Jonathan Henrique
	 * @date 28/11/2022
	 * 
	 * @return <code>List<Alternativa></code>
	 */
	List<Alternativa> findAllByTextoContainingIgnoreCase(@Param("texto") String texto);

	
	/**
	 * Método com query personalizada usando SQL
	 *  que remove o relacionamento da tabela de junção
	 *  TB_QUESTAO_ALTERNATIVAS
	 *  entre Questao e Alternativa
	 *  
	 *  a alternativa da lista da questao.
	 * 
	 * @author Jonathan Henrique
	 * @date 28/11/2022
	 * 
	 * @return <code>int<Alternativa></code> o número de rows alteradas
	 */
	@Modifying
	@Query(value = "DELETE  FROM TB_QUESTAO_ALTERNATIVAS WHERE QUESTAO_ID = ?1 AND ALTERNATIVA_ID = ?2", nativeQuery = true)
	int deleteAlternativaFromQuestao(Long questaoId, Long alternativaId);

}
