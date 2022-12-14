package com.etec.tcc.sprint_quiz.repository;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    List<Alternativa> findAllByTextoContainingIgnoreCase(@Param("texto") String texto);
    
    
    @Modifying
   	@Query(value = "DELETE  FROM TB_QUESTAO_ALTERNATIVAS1 WHERE QUESTAO_ID = ?1 AND ALTERNATIVA_ID = ?2",nativeQuery = true)
   	int deleteAlternativaFromQuestao(Long questaoId, Long alternativaId);

}
