package com.etec.tcc.sprint_quiz.repository;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.model.Alternativa;

/**
 * Classe de testes da camada de repositório Da AlternativaRepository
 * 
 * utilizado AssertJ para verificações.
 * 
 * @author jonathan
 * @date 11/01/2023
 */
@DataJpaTest // cria um banco h2 automático para os testes e sobe um contexto da aplicação
				// com o necessário
class AlternativaRepositoryTest {

//	private static final long ID = 1L;
//	private static final String TEXTO = "texto alternativa";
//	private static final String FOTO = "foto alternativa";
//	Alternativa alternativa;
	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);
	/**
	 * Injeção da classe a ser testada
	 */
	@Autowired
	private AlternativaRepository repository;

	/**
	 * Como AlternativaRepository está sendo testado não pode ser usado para
	 * verificar a sí próprio.
	 * 
	 * Por isso a utilização de uma outra classe para interagir com o Banco de
	 * Dados.
	 */
	@Autowired
	private TestEntityManager entityManager;

//	@BeforeAll
//	void start() throws Exception {
//		alternativa = new Alternativa(ID, TEXTO, FOTO);
////		repository.deleteAll();
////		repository.save(new Alternativa(0L,"Verde", "")); //com questao
//	}

//	@AfterAll
//	void end() throws Exception {
////		repository.deleteAll();
//	}

	@Test
	@DisplayName("Salva uma alternativa")
	void testSave_DeveriaSalvarUmaAnternativa_RetornaAlternativaSalva() {
		// cenário
		final long ID = 1L;
		final String TEXTO = "texto alternativa";
		final String FOTO = "foto alternativa";
		Alternativa alternativa = new Alternativa(ID, TEXTO, FOTO);

		// execucao
		Alternativa alternativaSalva = repository.save(alternativa);
		// verificacao
		LOGGER.info("alternativa criada - " + alternativaSalva);
		/**
		 * find() busca o obj criado no BD usando
		 * <hr>
		 * a Classe do obj
		 * <hr>
		 * id do obj criado
		 * <hr>
		 * e retorna o obj encontrado
		 */
		// sut -> System under Test
		Alternativa sut = entityManager.find(Alternativa.class, alternativaSalva.getId());

		assertThat(sut).isNotNull();
		assertThat(sut.getTexto()).isEqualTo(TEXTO);
		assertThat(sut.getFoto()).isEqualTo(FOTO);
		assertThat(sut.getId()).isEqualTo(ID);
		assertThat(sut).isEqualTo(alternativaSalva);
	}

}
