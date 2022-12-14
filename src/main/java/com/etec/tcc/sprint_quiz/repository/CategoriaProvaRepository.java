package com.etec.tcc.sprint_quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;

@Repository
public interface CategoriaProvaRepository extends JpaRepository<CategoriaProva,Long> {

    List<CategoriaProva> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
