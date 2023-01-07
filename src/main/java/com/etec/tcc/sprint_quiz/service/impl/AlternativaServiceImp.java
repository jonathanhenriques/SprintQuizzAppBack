package com.etec.tcc.sprint_quiz.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

@Service
@Transactional
public class AlternativaServiceImp implements AlternativaService {

	@Autowired
	private AlternativaRepository alternativaRepository;

//	@Autowired
//	private ObjectMapperUtils objectMapperUtils;

	@Autowired
	private QuestaoRepository questaoRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);

	/**
	 * @see AlternativaService#getAll()
	 */
	@Override
	public List<AlternativaDTO> getAll() {
		return ObjectMapperUtils.mapAll(alternativaRepository.findAll(), AlternativaDTO.class);
	}

	/**
	 * @see AlternativaService#findById(Long)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public AlternativaDTO getById(Long id) {
		return alternativaRepository.findById(id).map(a -> {
			AlternativaDTO dto = ObjectMapperUtils.map(a, AlternativaDTO.class);
			return dto;
		}).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));

	}

	/**
	 * @see AlternativaService#post(AlternativaDTO)
	 */
	// verificar se funciona
	@Override
	public AlternativaDTO post(AlternativaDTO alternativaDto) {
		return ObjectMapperUtils.map(
				alternativaRepository.save(ObjectMapperUtils.map(alternativaDto, Alternativa.class)),
				AlternativaDTO.class);
	}

	/**
	 * @see AlternativaService#put(AlternativaDTO)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public AlternativaDTO put(AlternativaDTO dto) {
		Alternativa a = alternativaRepository.findById(dto.getId())
				.orElseThrow(() -> new AlternativaNotFoundException(dto.getId().toString()));

		return ObjectMapperUtils.map(alternativaRepository.save(a), AlternativaDTO.class);

	}
	
	

	/**
	 * @see AlternativaService#deleteById(Long)
	 * @throws AlternativaNotFoundException
	 */
	@Override
	public void deleteById(Long id) {
		alternativaRepository.findById(id).orElseThrow(() -> new AlternativaNotFoundException(id.toString()));
		alternativaRepository.deleteById(id);

	}

	/**
	 * @see AlternativaService#getAllByTexto(String)
	 */
	@Override
	public List<AlternativaDTO> getAllByTexto(String texto) {
		return ObjectMapperUtils.mapAll(alternativaRepository.findAllByTextoContainingIgnoreCase(texto), AlternativaDTO.class);
	}

	/**
	 * @see AlternativaService#findById(Long) * @throws QuestaoNotFoundException
	 * @throws AlternativaNotFoundException
	 */
	public void removeAlternativaDeQuestao(Long questaoId, Long alternativaId) {
		Questao questao = questaoRepository.findById(questaoId)
				.orElseThrow(() -> new QuestaoNotFoundException(questaoId.toString()));

		alternativaRepository.findById(alternativaId)
				.orElseThrow(() -> new AlternativaNotFoundException(alternativaId.toString()));

		if (questao.getResposta().getId() == alternativaId)
			questao.setResposta(null);

		LOGGER.info("deletando da questaoId - " + questaoId + " a alternativaId" + alternativaId);
		alternativaRepository.deleteAlternativaFromQuestao(questaoId, alternativaId);
		LOGGER.info("excluindo relacionamento alternativa e questao lista...");
		alternativaRepository.deleteById(alternativaId);
		LOGGER.info("excluindo  alternativa - " + alternativaId);

	}
	

//	@Override
//	public List<Alternativa> postListaAlternativa(List<Alternativa> alternativas) {
//		Questao questao = questaoRepository.findById(alternativas.get(0).getQuestao().getId())
//				.orElseThrow(() -> new RegraNegocioException(
//						"Questão não encontrada | id:" + alternativas.get(0).getQuestao().getId()));
//
//		alternativas.forEach(a -> {
//			questao.getAlternativas().add(a);
//		});
//
//		alternativaRepository.saveAll(alternativas);
//
//		questaoService.putQuestao(questao);
//		return alternativas;
//	}

//	public List<Alternativa> postListaAlternativasComQuestaoSalva(List<Alternativa> alternativas) {
//		Questao questao = alternativas.get(0).getQuestao();
//
//		alternativas.forEach(a -> {
//			questao.getAlternativas().add(a);
//		});
//
//		alternativaRepository.saveAll(alternativas);
//
//		questaoService.putQuestao(questao);
//		return alternativas;
//	}


}
