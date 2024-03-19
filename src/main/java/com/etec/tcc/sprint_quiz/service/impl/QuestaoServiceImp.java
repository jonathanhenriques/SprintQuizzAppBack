package com.etec.tcc.sprint_quiz.service.impl;

import java.time.LocalDate;
import java.util.*;

import javax.validation.Valid;

import com.etec.tcc.sprint_quiz.api.assembler.MapperAssembler;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.dto.CategoriaQuestaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.api.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.api.exception.UsuarioNotFoundException;
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
@RequiredArgsConstructor
@Service
@Transactional
public class QuestaoServiceImp implements QuestaoService {

	private final QuestaoRepository questaoRepository;

	private final CategoriaQuestaoRepository categoriaQuestaoRepository;

	private final UsuarioRepository usuarioRepository;

	private final AlternativaRepository alternativaRepository;

	@Lazy
	private final AlternativaService alternativaService;

	private final MapperAssembler mapperAssembler;




//	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);

	@Override
	public Page<QuestaoDTO> getAll(Pageable pageable) {
		Page<Questao> lista = questaoRepository.findAll(pageable);
		List<QuestaoDTO> listaDTO = mapperAssembler.converteListDeQuestoesParaListDequestoesDTO(lista.getContent());
		Page<QuestaoDTO> pageListaQuestaoDTO = new PageImpl<>(listaDTO, pageable, lista.getTotalElements());

		Page<QuestaoDTO> pageCategoriaQuestaoDTO =
				new PageImpl<>(listaDTO, pageable, lista.getTotalElements());



		return pageCategoriaQuestaoDTO;
	}

	@Override
	public Questao getById(@PathVariable Long id) {
		return questaoRepository.findById(id).map(q -> q)
				.orElseThrow(() -> new QuestaoNotFoundException(id.toString()));
	}

	@Override
	public Questao findByIdFetch(Long id) {
		return questaoRepository.findByIdFetch(id)
				.orElseThrow(QuestaoNotFoundException::new);
	}

	@Override
	public Optional<Questao> findByIdWithFetch(Long id) {
		return questaoRepository.findByIdWithFetch(id);

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
	public QuestaoDTO postQuestao(@Valid @RequestBody QuestaoDTO questao) {
		usuarioRepository.findById(questao.getCriadorId())
				.orElseThrow(() -> new UsuarioNotFoundException());
//						questao.getCriador().getId().toString()));


//		List<Long> idsAlternativas = new ArrayList<>();
//		idsAlternativas = questao.getAlternativas().stream().map(a -> {
//			long id;
//			id = a.getId();
//			return id;
//		}).collect(Collectors.toList());
//
//		Set<AlternativaDTO> setAlternativas = new HashSet<>();
//		for (int j = 0; j < idsAlternativas.size(); j++){
//			setAlternativas.add(alternativaService.getById(idsAlternativas.get(j)));
//		}
//
//		Set<AlternativaDTO> listaDTO = setAlternativas;
//
//		questao.setAlternativas(null);
//		Set<Alternativa>listaAlternativasConvertidas = listaDTO.stream().map(dto -> {
//			Alternativa a = new Alternativa();
//			a.setId(dto.getId());
//			a.setTexto(dto.getTexto());
//			a.setFoto(dto.getFoto());
//			return a;
//
//		}).collect(Collectors.toSet());
//
//		questao.setAlternativas(listaAlternativasConvertidas);

		return categoriaQuestaoRepository.findById(questao.getIdCategoriaQuestao())
				.map(c -> {
					Questao questaoRequest = mapperAssembler.converteQuestaoDTOParaQuestao(questao);
					QuestaoDTO questaoResponse = mapperAssembler.converteQuestaoParaQuestaoDTO(questaoRepository.save(questaoRequest));
					return questaoResponse;
				}).orElseThrow(CategoriaQuestaoNotFoundException::new);

	}

//	private Questao salvaQuestao(Questao questao) {
//		usuarioRepository.findById(questao.getCriador().getId())
//				.orElseThrow(() -> new UsuarioNotFoundException(questao.getCriador().getId().toString()));
//		return categoriaQuestaoRepository.findById(questao.getCategoria().getId())
//				.map(c -> questaoRepository.save(questao))
//				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(questao.getCategoria().getId().toString()));
//	}

	@Transactional
	public QuestaoDTO adicionarAlternativaEmQuestao(QuestaoDTO questao) {

		Questao questaoRequest = mapperAssembler.converteQuestaoDTOParaQuestao(questao);
		List<Alternativa> listaAlternativas = new ArrayList<>(questaoRequest.getAlternativas());
		Questao questaoDoBanco = questaoRepository.findById(questaoRequest.getId()).orElseThrow(QuestaoNotFoundException::new);
//		List<Alternativa> converteAlternativasQuestao = new ArrayList<>(questaoDoBanco.getAlternativas());
//		int quantidadeAlternativas = listaAlternativas.size() + converteAlternativasQuestao.size();
//
//		if(!questaoDoBanco.getAlternativas().isEmpty()) {
//			for(int j = 0; j < converteAlternativasQuestao.size(); j++) {
//				listaAlternativas.add(converteAlternativasQuestao.get(j));
//			}
//		}

		List<Alternativa> novasAlternativas = new ArrayList<>();
		for(int j = 0; j < listaAlternativas.size();j++) {
			Optional<Alternativa> alternativa = Optional.ofNullable(listaAlternativas.get(j));
			alternativa = alternativaRepository.findById(alternativa.get().getId());
			novasAlternativas.add(alternativa.get());
		}
		questaoRequest.setAlternativas(null);
//		List<Alternativa> listaAlternativasSalvar = new HashSet<>(novasAlternativas);
		questaoRequest.setAlternativas(novasAlternativas);

		String textoQuestao = questaoRepository.findById(questaoRequest.getId()).get().getTexto();
		questaoRequest.setTexto(textoQuestao);
		QuestaoDTO dto = mapperAssembler.converteQuestaoParaQuestaoDTO(questaoRequest);

		return postQuestao(dto);
	}


	@Override
	public QuestaoDTO putQuestao(@Valid @RequestBody QuestaoDTO dto) {
		Questao questaoBanco = questaoRepository.findById(dto.getId()).orElseThrow(() -> new QuestaoNotFoundException(dto.getId().toString()));
		List<Alternativa> listaAlternativa = new ArrayList<>();
		if(questaoBanco.getAlternativas() != null){
			listaAlternativa = questaoBanco.getAlternativas();
		}
//		}

		Questao questaoRequest = mapperAssembler.converteQuestaoDTOParaQuestao(dto);
		questaoRequest = questaoRepository.save(questaoRequest);

		return mapperAssembler.converteQuestaoParaQuestaoDTO(questaoRequest);


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
