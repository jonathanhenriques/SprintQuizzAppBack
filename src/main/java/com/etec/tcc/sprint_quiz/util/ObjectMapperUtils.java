package com.etec.tcc.sprint_quiz.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;

@Component
public class ObjectMapperUtils {

//	@Autowired
	private static ModelMapper modelMapper;
	
	@Autowired
	private static AlternativaRepository alternativaRepository;
	


static {
    modelMapper = new ModelMapper();
//    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    
	Converter<HashSet<AlternativaDTO>, HashSet<Alternativa>> setAlternativaDTOParaSetAlternativaConverter = obj -> {
		return obj.getSource().stream().map(dto -> {

			Alternativa alternativa = alternativaRepository.findById(dto.getId())
					.orElseThrow(() -> new AlternativaNotFoundException(obj.toString()));
			return alternativa;

		}).collect(Collectors.toCollection(HashSet::new));
//		}).collect(Collectors.toSet());

	};
	
	
	
	modelMapper.createTypeMap(QuestaoDTO.class, Questao.class)
	.addMappings(mapper -> mapper.using(setAlternativaDTOParaSetAlternativaConverter)
			.map(QuestaoDTO::getAlternativas, Questao::setAlternativas));
    
}

private ObjectMapperUtils() {
}

	/**
	 * <p>
	 * Nota: O objeto da  classe de saída deve ter um construtor padrão sem argumentos
	 * </p>
	 *
	 * @param <D>      tipo do objeto de saída.
	 * @param <T>      tipo do objeto de entrada que será mapeado.
	 * @param entidade   entidade que será mapeada.
	 * @param tipoClasseSaida classe do objeto de saída.
	 * @return new objeto do tipo <code>tipoClasseSaida</code>.
	 */
	public <D, T> D map(final T entidade, Class<D> tipoClasseSaida) {
		return modelMapper.map(entidade, tipoClasseSaida);
	}

	/**
	 * <p>
	 * Nota: o objeto de saída deve ter um construtor padrão sem argumentos
	 * </p>
	 *
	 * @param listaDeEntrada lista de entidades que precisam ser mapeadas
	 * @param classeDeSaida   classe do resultado do mapeamento
	 * @param <D>        classe para a qual a lista será mapeada
	 * @param <T>        tipo da entrada <code>listaDeEntrada</code>
	 * @return lista de objetos mapeados para <code> <D> </code>.
	 */
	public <D, T> List<D> mapAll(final Collection<T> listaDeEntrada, Class<D> classeDeSaida) {
		return listaDeEntrada.stream().map(obj -> map(obj, classeDeSaida)).collect(Collectors.toList());
	}

	/**
	 * Mapeia {@code entrada} para {@code saida}.
	 *
	 * @param entrada objeto que será mapeado
	 * @param saida objeto para mapear para
	 */
	public <S, D> D map(final S entrada, D saida) {
		modelMapper.map(entrada, saida);
		return saida;
	}
}
