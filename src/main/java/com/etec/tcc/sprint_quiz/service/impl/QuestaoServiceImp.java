package com.etec.tcc.sprint_quiz.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.etec.tcc.sprint_quiz.service.QuestaoService;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequiredArgsConstructor
@Service
@Transactional
public class QuestaoServiceImp implements QuestaoService {

	@Autowired
	private QuestaoRepository questaoRepository;

	@Autowired
	private CategoriaQuestaoRepository categoriaQuestaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AlternativaRepository alternativaRepository;

	@Autowired
	@Lazy
	private AlternativaService alternativaService;

	@Autowired
	private ObjectMapperUtils modelMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);

	@Override
	public List<QuestaoDTO> getAll() {
		List<Questao> lista = questaoRepository.findAll();
		List<QuestaoDTO> listaDTO = (List<QuestaoDTO>) modelMapper.map(lista, QuestaoDTO.class);// VERIFICAR SE FUNCIONA
		return listaDTO;
	}

	@Override
	public Questao getById(@PathVariable Long id) {
		return questaoRepository.findById(id).map(q -> q)
				.orElseThrow(() -> new QuestaoNotFoundException(id.toString()));
	}

	@Override
	public List<Questao> getAllByTexto(@PathVariable String texto) {
		return questaoRepository.findAllByTextoContainingIgnoreCase(texto);
	}

	@Override
	public List<Questao> getAllByInstituicao(@PathVariable String instituicao) {
		return questaoRepository.findAllByInstituicaoContainingIgnoreCase(instituicao);
	}

	@Override
	public List<Questao> findAllByAno(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ano) {
		return questaoRepository.findAllByAno(ano);
	}

	@Override
	public List<Questao> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
		return questaoRepository.findAllByAnoBetween(anoInicial, anoFinal);
	}

	@Override
	public List<Questao> findAllByAntesAno(@PathVariable LocalDate ano) {
		return questaoRepository.findAllByAnoBefore(ano);
	}

	@Override
	public List<Questao> getQuestoesByCriadorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).map(u -> questaoRepository.findAllByCriadorId(id))
				.orElseThrow(() -> new UsuarioNotFoundException(id.toString()));
	}

	@Override
	public QuestaoDTO postQuestao(@Valid @RequestBody QuestaoDTO questaoDTO) {
		usuarioRepository.findById(questaoDTO.getCriadorId())
				.orElseThrow(() -> new UsuarioNotFoundException(questaoDTO.getCriadorId()));

		categoriaQuestaoRepository.findById(questaoDTO.getIdCategoriaQuestao())
				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(questaoDTO.getCriadorId()));

		Questao questaoSalva = questaoRepository.save(modelMapper.map(questaoDTO, Questao.class));
		return modelMapper.map(questaoSalva, QuestaoDTO.class);

	}

	@Transactional
	public Questao salvarQuestaoComAlternativa(@RequestBody Questao questao) {
//        List<Alternativa> alternativas = questao.getAlternativas();
//        questao.setAlternativas(new ArrayList<Alternativa>());
//        postQuestao(questao);
//
//        List<Alternativa> listaAlternativasComQuestao = alternativas.stream().map(a -> {
//            a.setQuestao(questao);
//            return a;
//        }).collect(Collectors.toList());
//
//        alternativaService.postListaAlternativasComQuestaoSalva(listaAlternativasComQuestao);

		return questao;
	}

	@Override
	public QuestaoDTO putQuestao(@Valid @RequestBody QuestaoDTO dto) {
		questaoRepository.findById(dto.getId()).orElseThrow(() -> new QuestaoNotFoundException(dto.getId().toString()));
		for (AlternativaDTO a : dto.getAlternativas()) {
			alternativaRepository.findById(a.getId())
					.orElseThrow(() -> new AlternativaNotFoundException(a.getId().toString()));
		}

//		Alternativa resposta =  alternativaRepository.findById(dto.getResposta().getId()).orElseThrow(() -> new AlternativaNotFoundException(dto.getId().toString()));
//		Questao questaoRequest = modelMapper.map(dto, Questao.class);
		Questao questaoRequest = modelMapper.map(dto, Questao.class);////////
//		questaoRequest.setResposta(resposta);
//		questaoRepository.save(questaoRequest);
		QuestaoDTO dtoResponse = modelMapper.map(questaoRepository.save(questaoRequest), QuestaoDTO.class);
//		dtoResponse.setResposta(modelMapper.map(resposta, AlternativaDTO.class));

		return dtoResponse;

	}

//	@Override
//	public QuestaoDTO putQuestao(@Valid @RequestBody QuestaoDTO dto) {
//		questaoRepository.findById(dto.getId()).orElseThrow(() -> new QuestaoNotFoundException(dto.getId().toString()));
//		for (AlternativaDTO a : dto.getAlternativas()) {
//			alternativaRepository.findById(a.getId()).orElseThrow(() -> new AlternativaNotFoundException(a.getId().toString()));
//		}
//		
//		Alternativa resposta =  alternativaRepository.findById(dto.getIdCategoriaQuestao()).orElseThrow(() -> new AlternativaNotFoundException(dto.getId().toString()));
////		LOGGER.info("respostaId - " + dto.getRespostaId());
////		Set<AlternativaDTO> listaAlternativas = dto.getAlternativas();
////		Set<Alternativa> listaAlternativas = converteListaAlternativaDTOParaListaAlternativa(dto.getAlternativas());
//		
//		
//		Set<Alternativa> listaAlternativas = new HashSet<Alternativa>();
//		for (AlternativaDTO a : dto.getAlternativas()) {
//			Alternativa alternativa = alternativaRepository.findById(a.getId()).orElseThrow(() -> new AlternativaNotFoundException(a.getId().toString()));
//			LOGGER.info("texto alternativa - " + alternativa.getTexto());
//			listaAlternativas.add(alternativa);
//			
//		}
//		
//		Questao questaoRequest = modelMapper.map(dto, Questao.class);
//		questaoRequest.setAlternativas(listaAlternativas);
//		questaoRequest.setResposta(resposta);
//		questaoRepository.save(questaoRequest);
//		QuestaoDTO dtoResponse = modelMapper.map(questaoRequest, QuestaoDTO.class);
//		dtoResponse.setAlternativas(converteListaAlternativaParaListaAlternativaDTO(listaAlternativas));
////		dtoResponse.setIdCategoriaQuestao(resposta.getId());
////		questaoSalvar.setAlternativas(questaoRequest);
//		dtoResponse.setIdResposta(modelMapper.map(resposta, AlternativaDTO.class));
//		
//		
////		Alternativa respo = alternativaRepository.findById(questaoSalvar.getResposta().getId()).orElseThrow(() -> new AlternativaNotFoundException(questaoSalvar.getId().toString()));
////		questaoSalvar.setResposta(respo);
//		
////		Questao qSalva = questaoRepository.save(questaoSalvar);
//		
////		qSalva.getResposta().setId(resposta.getId());
//		
//		
////		QuestaoDTO dtoRes = modelMapper.map(qSalva, QuestaoDTO.class);
////		Set<AlternativaDTO> listaDTO = converteListaAlternativaParaListaAlternativaDTO(listaAlternativas);
////		dtoRes.setAlternativas(listaDTO);
//		
//		
////		dtoRes.setRespostaId(resposta.getId());
//		
//		return dtoResponse;
//		
//	}

//	public Set<AlternativaDTO> converteListaAlternativaParaListaAlternativaDTO(Set<Alternativa> alternativas) {
//		return alternativas.stream().map(a -> new AlternativaDTO(a)).collect(Collectors.toSet());
//	}
//	
//	public Set<Alternativa> converteListaAlternativaDTOParaListaAlternativa(Set<AlternativaDTO> alternativas) {
//		return alternativas.stream().map(a -> new Alternativa(a)).collect(Collectors.toSet());
//	}

	@Override
	public void deleteQuestao(@PathVariable Long id) {
		questaoRepository
				.delete(questaoRepository.findById(id).orElseThrow(() -> new QuestaoNotFoundException(id.toString())));
	}

//    @Override
//    @Transactional
//    public ResponseEntity<Questao> salvarQuestao(@RequestBody Questao questao){
////        Prova idProva = questao.get
//        if(categoriaQuestaoRepository.existsById(questao.getCategoria().getId())){
//
//            //setando id da questao nas alternativas
////            Alternativa alternativa = new Alternativa();
//            List<Alternativa> listaAlternativas = questao.getAlternativas()
//                    .stream()
//                    .map(a -> {
//                        a.setQuestao(questao);
//                        return a;
//                    }).collect(Collectors.toList());
//            questao.setAlternativas(listaAlternativas);
//
//            //setando id da questao nas alternativas
//
//
//            questaoRepository.save(questao);
//            alternativaRepository.saveAll(listaAlternativas);
////            questao.setAlternativas(listaAlternativas);
//
//
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(questao);
//
//        } else {
//            throw new RegraNegocioException("Categoria não encontrada! id:" + questao.getCategoria().getId());
//        }
//    }

//    private List<Alternativa>  salvarAlternativas(List<Alternativa> alternativas){
//        if(alternativas.isEmpty())
//            throw new RegraNegocioException("Não é possível criar uma questão sem alternativas");
//
//
//
//
//    }

//    public Questao converterParaQuestao(QuestaoDTO dto) {
//        Questao q = new Questao();
//
//        q.setId(dto.getId());
//        q.setInstituicao(dto.getInstituicao());
//        dto.setAno(String.valueOf(LocalDate.now()));
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        LocalDate ld = LocalDate.parse(dto.getAno(), formatter);
////        q.setAno(ld);
//
//        q.setTexto(dto.getTexto());
//        q.setOpcao_1(dto.getOpcao_1());
//        q.setOpcao_2(dto.getOpcao_2());
//        q.setOpcao_3(dto.getOpcao_3());
//        q.setOpcao_4(dto.getOpcao_4());
//        q.setOpcao_5(dto.getOpcao_5());
//        q.setResposta(dto.getResposta());
//
//        CategoriaQuestao cq = categoriaQuestaoRepository.findById(dto.getCategoria())
//                .orElseThrow(() -> new RegraNegocioException("Código da categoria da questão inválido!"));
//        q.setCategoria(cq);
//
//        Usuario u = usuarioRepository.findById(dto.getCriador())
//                .orElseThrow(() -> new RegraNegocioException("Código do criador(usuario) inválido!"));
//        q.setCriador(u);
//        return q;
//    }
}
