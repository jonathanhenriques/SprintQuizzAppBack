package com.etec.tcc.sprint_quiz.configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.CategoriaQuestao;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.QuestaoProva;
import com.etec.tcc.sprint_quiz.model.Role;
import com.etec.tcc.sprint_quiz.model.Usuario;
//import com.etec.tcc.sprint_quiz.model.testemodels.AlternativaTeste;
//import com.etec.tcc.sprint_quiz.model.testemodels.AlternativaTesteRepository;
//import com.etec.tcc.sprint_quiz.model.testemodels.QuestaoTeste;
//import com.etec.tcc.sprint_quiz.model.testemodels.QuestaoTesteRepository;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.repository.CategoriaQuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoProvaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;

import lombok.extern.slf4j.Slf4j;

/**
 * classe para povoar o banco h2 quando em abiente Teste usando
 * application-Teste.properties
 * 
 * @author hsjon
 * @since 20/12/2022
 *
 */
@Slf4j
@Teste
public class TesteConfigBd {

	@Autowired
	private AlternativaRepository alternativaRepository;

	@Autowired
	private QuestaoRepository questaoRepository;

	@Autowired
	private ProvaRepository provaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaProvaRepository categoriaProvaRepository;

	@Autowired
	private CategoriaQuestaoRepository categoriaQuestaoRepository;

	@Autowired
	private QuestaoProvaRepository questaoProvaRepository;

	@Autowired
	private QuestaoService questaoService;

//	@Autowired
//	private QuestaoTesteRepository questaoTesteRepository;

//	@Autowired
//	private AlternativaTesteRepository alternativaTesteRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);
	/**
	 * Expression language pegando versao do java
	 * @Value("#{systemProperties['java.version']}")
	 */
	@Value("#{systemProperties['java.version']}")
	private String versao;

	@Bean
	public CommandLineRunner commandLineRunner(/* @Autowired ProvaRepository provaRepository */) {
		return args -> {
			LOGGER.info(versao + "******versao*****");
			LOGGER.info("*********************Rodando Ambiente TESTE***************************");

		};

	}

//	@Bean
//	public void startDb() {
//
//		// Long id, String nome, String usuario, String senha, String foto, String tipo,
//		// List<Questao> questoes, List<Prova> provas
//		List<Questao> lq = new ArrayList<>();
//		List<Prova> lp = new ArrayList<>();
//
//		log.info("#######CARGA USUARIO#######");
//		Role role = new Role("ROLE_ADMIN");
//		Collection<Role> roles = new ArrayList<>();
//		roles.add(role);
//
//		String permission = "ACCESS_TEST1";
//		Collection<String> permissions = new ArrayList<String>();
//		Usuario usuario1 = new Usuario(null, "madruguinha", "madruguinha@email.com", "$2a$10$BIcPtA.B3OXCkpTFVWdoB.YH4/Bkip6lOP02qi0GzwZ/YN7nAfz3S", "", roles, lq, lp);
////        Usuario usuario1 = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", 0, permissions, roles, new ArrayList<Questao>(), new ArrayList<Prova>());
//		// Long id, String titulo, String descricao, List<Prova> provas
//
//		usuarioRepository.save(usuario1);
//
//		log.info("#######CARGA CATEGORIAPROVA#######");
//		CategoriaProva cp = new CategoriaProva(null, "Categoria Prova teste", "Descrição categoriaProva", null);
//		CategoriaProva cp1 = new CategoriaProva(null, "Categoria Prova teste 2", "Descrição categoriaProva 2", null);
//
//		categoriaProvaRepository.save(cp);
//		categoriaProvaRepository.save(cp1);
//
//		log.info("#######CARGA CATEGORIAQUESTAO#######");
//		CategoriaQuestao cq = new CategoriaQuestao(null, "Categoria questao teste", "Descrição categoria questao",
//				Arrays.asList(new Questao()));
//		CategoriaQuestao cq1 = new CategoriaQuestao(null, "Categoria questao teste 2", "Descrição categoria questao 2",
//				Arrays.asList(new Questao()));
//
//		categoriaQuestaoRepository.save(cq);
//		categoriaQuestaoRepository.save(cq1);
//
//		log.info("#######CARGA QUESTAO#######");
//		Alternativa resposta = new Alternativa(null, "texto alternativa alpha", "");
//		alternativaRepository.save(resposta);
//		Set<Alternativa> alternativas = new HashSet<>();
//		Alternativa a7 = new Alternativa(null, "texto alternativa y", "");
//		alternativaRepository.save(a7);
//		alternativas.add(a7);
////		alternativaRepository.save(resposta);
//		Questao questao1 = new Questao(null, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
//				DificuldadeQuestao.FACIL, alternativas, resposta, cq1, usuario1);
//		Questao questao2 = new Questao(null, "instituicao ", LocalDate.now(), "imagem", "Texto questao2 teste",
//				DificuldadeQuestao.INTERMEDIARIO, null, null, cq1, usuario1);
//		Questao q = new Questao(null, null, null, null, null, null, null, null, cq1, usuario1);
//
//		questaoRepository.save(questao1);
//		questaoRepository.save(questao2);
//
//		log.info("#######CARGA PROVA#######");
//		// Long id, String nome, String descricao, Integer duracao, Usuario usuario,
//		// List<QuestaoProva> questoes, String instituicao, CategoriaProva categoria
//		Prova prova1 = new Prova(null, "Prova teste", "Prova descrição da prova teste", 1, usuario1, null,
//				"instituição teste", cp);
//
//		provaRepository.save(prova1);
//
//		log.info("#######CARGA QUESTAOPROVA#######");
//		QuestaoProva qp1 = new QuestaoProva(null, questao1, prova1);
//		QuestaoProva qp2 = new QuestaoProva(null, questao2, prova1);
//
//		questaoProvaRepository.save(qp1);
//		questaoProvaRepository.save(qp2);
//
//		prova1.setQuestoes(Arrays.asList(qp1, qp2));
//		provaRepository.save(prova1);
////
//		log.info("#######CARGA CATEGORIAPROVA#######");
//		// Long id, String texto, String foto, Questao questao
////		Alternativa a1 = new Alternativa(null, "texto alternativa aasdasdasdasd", "asdfasdfsdfsdf");
////		Alternativa a2 = new Alternativa(null, "texto alternativa basdasdasdasd", "");
////		Alternativa a3 = new Alternativa(null, "texto alternativa c", "");
//
////        
////        questao1.setAlternativas(List.of(a1, a2, a3));
////        questao2.setAlternativas(List.of(a1, a2, a3));
////
//
//	}

}
