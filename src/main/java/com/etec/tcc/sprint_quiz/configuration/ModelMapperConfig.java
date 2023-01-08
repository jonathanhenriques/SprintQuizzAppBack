//package com.etec.tcc.sprint_quiz.configuration;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.modelmapper.Converter;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//
//import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
//import com.etec.tcc.sprint_quiz.model.Alternativa;
//import com.etec.tcc.sprint_quiz.model.Questao;
//import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
//import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
//import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
//
//import lombok.Data;
//
//
//@Data
//@Configuration
//public class ModelMapperConfig {
//	
//
//	@Autowired
//	@Lazy
//	private AlternativaRepository alternativaRepository;
//	
//	@Bean
//	public ModelMapper modelMapper() {
//		var modelMapper = new ModelMapper();
//		
//		
//		
//		
//		
//		Converter<HashSet<AlternativaDTO>, HashSet<Alternativa>> setAlternativaDTOParaSetAlternativaConverter = obj -> {
//			return obj.getSource().stream().map(dto -> {
//
//				Alternativa alternativa = alternativaRepository.findById(dto.getId())
//						.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));
//				return alternativa;
//
//			}).collect(Collectors.toCollection(HashSet::new));
////			}).collect(Collectors.toSet());
//
//		};
//		
//		
//		
//		
//		modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
//		.addMappings(mapper -> mapper.using(setAlternativaDTOParaSetAlternativaConverter)
//				.map(QuestaoDTO::getAlternativas, Questao::setAlternativas));
//
//		return modelMapper;
//
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	private Set<AlternativaDTO> converteSetAlternativaParaSetAlternativaDTO(Set<Alternativa> alternativas) {
////		return alternativas.stream().map(a ->  converteParaAlternativaDTO(a)).collect(Collectors.toSet());
////	}
////	
////	private Set<Alternativa> converteSetAlternativaDTOParaSetAlternativa(Set<AlternativaDTO> dtos) {
////		return dtos.stream().map(a ->  converteParaAlternativa(a)).collect(Collectors.toSet());
////	}
////	
////	private Alternativa converteParaAlternativa(AlternativaDTO dto) {
////		Alternativa a = new Alternativa();
////		a.setId(dto.getId());
////		a.setFoto(dto.getFoto());
////		a.setTexto(dto.getTexto());
////		return a;
////	}
////	
////	private AlternativaDTO converteParaAlternativaDTO(Alternativa alternativa) {
////		AlternativaDTO dto = new AlternativaDTO();
////		dto.setId(alternativa.getId());
////		dto.setFoto(alternativa.getFoto());
////		dto.setTexto(alternativa.getTexto());
////		return dto;
////	}
////	
////	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	/**
////	 * Converter do ModelMapper para converter AlternativaDTO para Alternativa
////	 */
////	Converter<Long, CategoriaQuestao> longCategoriaParaCategoriaQuestao = obj -> {
////		CategoriaQuestao categoria = null;
////		categoria = categoriaQuestaoRepository.findById(obj.getSource())
////				.orElseThrow(() -> new CategoriaQuestaoNotFoundException(obj.toString()));
////
////		return categoria;
////	};
//
//
////		/**
////		 * Conversor que ensina o ModelMapper a mapear de um atributo para outro. Long
////		 * resposta(contêm id de uma alternativa), para Alternativa resposta. De
////		 * QuestaoDTO para Questao
////		 */
////		Converter<Long, Alternativa> LongParaAlternativaConverter = obj -> {
////			Alternativa alternativa = null;
////			alternativa = alternativaRepository.findById(obj.getSource())
////					.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));
////
////			return alternativa;
////		};
//
//}
