package com.etec.tcc.sprint_quiz.model.dto.verificacoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.configuration.Teste;
import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

import lombok.extern.slf4j.Slf4j;



/**
 * classe para testar códigos livremente 
 * 
 * @author hsjon
 * @since 01/01/2023
 *
 */
@Slf4j
@Teste
public class Verificacoes {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);
	
	@Autowired
	private ObjectMapperUtils objectMapperUtils;

//	@Bean
	public CommandLineRunner commandLineRunnerTestesLivres() {
		
		return args -> {
			LOGGER.info("*********************Início códigos de TESTES livres***************************");
//			System.out.println(new BCryptPasswordEncoder().encode("12345678")); //gerar senhas para salvar no banco usuários iniciais

			
		System.out.println("#######CARGA USUARIO#######");
		
//		Role role = new Role("ROLE_ADMIN");
//		Collection<Role> roles = new ArrayList<>();
//		roles.add(role);
		Usuario usuario1 = new Usuario(1L, "madruguinha", "madruguinha@email.com", "$2a$10$BIcPtA.B3OXCkpTFVWdoB.YH4/Bkip6lOP02qi0GzwZ/YN7nAfz3S", "", new ArrayList<Role>(), new ArrayList<Questao>(), new ArrayList<Prova>());
		LOGGER.info("usuario1 - \n"+usuario1);
//		System.out.println("#######CARGA CategoriaQuestao#######");
		CategoriaQuestao cq1 = new CategoriaQuestao(1L, "Categoria questao teste 2", "Descrição categoria questao 2",
				Arrays.asList(new Questao()));
		
//		System.out.println("#######CARGA Questao#######");
		Alternativa resposta = new Alternativa(1L, "texto alternativa alpha", "");
//		alternativaRepository.save(resposta);
		Set<Alternativa> alternativas = new HashSet<>();
		Alternativa a7 = new Alternativa(1L, "texto alternativa y", "");
//		alternativaRepository.save(a7);
		alternativas.add(a7);
//		alternativaRepository.save(resposta);
		
		Questao questao1 = new Questao(1L, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
				DificuldadeQuestao.FACIL, alternativas, resposta, cq1, usuario1);
		LOGGER.info("\nquestaoOriginal - \n"+questao1);
		QuestaoDTO questaoDTO = objectMapperUtils.map(questao1, QuestaoDTO.class);
		LOGGER.info("\nquestaoDTOMapper - \n"+questaoDTO);
//		Questao q = modelMapper.map(questaoDTO, Questao.class);
//		ObjectMapperUtils.map(questaoDTO, Questao.class);
//		LOGGER.info("\nqMapper - \n"+q);
		
		LOGGER.info("*********************ObjectModelMapper TESTES livres***************************");
		Questao questao2 = new Questao(1L, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
				DificuldadeQuestao.FACIL, alternativas, resposta, cq1, usuario1);
//		LOGGER.info("\nquestaoOriginal - \n"+questao2);
//		QuestaoDTO questaoDTO2 = ObjectMapperUtils.map(questao2, QuestaoDTO.class);
//		LOGGER.info("\nquestaoDTOMapper - \n"+questaoDTO2);
//		Questao questaoVolta = ObjectMapperUtils.map(questaoDTO2, Questao.class);
//		LOGGER.info("\nquestaoVolta - \n"+questaoVolta);
//		LOGGER.info("*********************FIM códigos de TESTES livres***************************");
		
		};
	}

}
