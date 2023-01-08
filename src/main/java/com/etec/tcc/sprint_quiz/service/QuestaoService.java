package com.etec.tcc.sprint_quiz.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;

public interface QuestaoService {

	List<QuestaoDTO> getAll();

	Questao getById(@PathVariable Long id);

	List<Questao> getAllByTexto(@PathVariable String texto);

	List<Questao> getAllByInstituicao(@PathVariable String instituicao);

	List<Questao> findAllByAno(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ano);

	List<Questao> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal);

	List<Questao> findAllByAntesAno(@PathVariable LocalDate ano);

	List<Questao> getQuestoesByCriadorId(@PathVariable Long criadorId);

	QuestaoDTO postQuestao(@Valid @RequestBody QuestaoDTO questao);

	Questao salvarQuestaoComAlternativa(@RequestBody Questao questao);

	QuestaoDTO putQuestao(@Valid @RequestBody QuestaoDTO questao);

	void deleteQuestao(@PathVariable Long id);

}
