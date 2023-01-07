package com.etec.tcc.sprint_quiz.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * Classe de teste do Servico AlternativaService
 * 
 * @author hsjon
 * @date 26/12/2022
 */
@Slf4j
@SpringBootTest // anotacao para sinalizar ao spring que e uma classe de test
class AlternativaServiceImpTest {

	private static final long ID = 1L;
	private static final long QUESTAO_ID = 1L;
	private static final String TEXTO_ALTERNATIVA = "texto alternativa a";
	private static final Logger LOGGER = LoggerFactory.getLogger(AlternativaServiceImpTest.class);

	/**
	 * Classes consumidas no service aqui elas seram mockadas
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private Alternativa alternativa;
	private AlternativaDTO alternativaDTO;
	private Optional<Alternativa> alternativaOptional;
	private Optional<Questao> questaoOptional;

	/**
	 * dependencia da classe que sera testada
	 */
	@InjectMocks // cria uma instancia real, ja que é a classe que está sendo testada
	private AlternativaServiceImp alternativaServiceImpl;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaRepository alternativaRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private QuestaoRepository questaoRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@Mock // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private ModelMapper modelMapper;

	/**
	 * metodo utilizado para inicializar recursos antes de cada teste ser rodado
	 */
	@BeforeEach // indica que este metodo rodara antes de cada um dos testes
	void setUp() {
		MockitoAnnotations.openMocks(this); // inicializa os Mocks dessa classe,passando a própria classe, ele já
											// inicializa todos os mocks necessários
		startCarga(); // cria os objetos na memória para os testes
	}

	@Test
	@DisplayName("Busca Todas as Alternativas")
	void testGetAllBuscaTodasAsAlternativas() {
		// cenario
		when(alternativaRepository.findAll()).thenReturn(List.of(alternativa, alternativa));

		// execucao
		List<AlternativaDTO> lista = alternativaServiceImpl.getAll();

		// verificaco
		assertNotNull(lista);
		assertEquals(2, lista.size());
		assertEquals(Alternativa.class, lista.get(0).getClass());
		assertEquals(ID, lista.get(0).getId());
		assertEquals(TEXTO_ALTERNATIVA, lista.get(0).getTexto());
		assertEquals("", lista.get(0).getFoto());
//		assertEquals(QUESTAO_ID, lista.get(0).getQuestao().getId());
		assertEquals(List.of(alternativa, alternativa), lista);
	}

	@Test
	@DisplayName("Busca uma alternativa pelo id") // define um nome personalizado para o método
	void testGetByIdBuscaUmaAlternativaPorId() {

		/**
		 * Quando o método findById() de AlternativaRepository for chamado passando
		 * qualquer Long, então retorne o que eu passar
		 */
		// cenário
		Mockito.when(alternativaRepository.findById(Mockito.anyLong())).thenReturn(alternativaOptional);

		/**
		 * Como o serviço já foi mockado é feita a chamada, como se fosse a classe real
		 */
		// execução
		AlternativaDTO response = alternativaServiceImpl.getById(ID);

		/**
		 * É feita a verificação comparando o que é esperado com a resposta
		 */
		// verificação
		Assertions.assertNotNull(response); // sem import static
		assertEquals(alternativa.getClass(), response.getClass()); // compara dois objs, valores...
		assertEquals(ID, response.getId());
//		assertEquals(QUESTAO_ID, response.getQuestao().getId());
		assertEquals(TEXTO_ALTERNATIVA, response.getTexto());

	}

	@Test
	@DisplayName("Busca uma alternativa pelo id e retorna AlternativaNotFoundException com o id buscado na mensagem")
	void testGetByIdBuscaUmaAlternativaPorIdERetornaAlternativaNotFoundExceptionComIdNaMensagem() {

		// cenário
		when(alternativaRepository.findById(anyLong())).thenThrow(new AlternativaNotFoundException(String.valueOf(ID)));

		// execução
		try {
			alternativaServiceImpl.getById(anyLong());
			fail();
		} catch (Exception e) {
			// verificacao
			assertEquals(AlternativaNotFoundException.class, e.getClass());
			assertEquals("Alternativa não encontrada! | " + ID, e.getMessage());
		}

	}

	@Test
	@DisplayName("Busca uma alternativa prlo id e retorna AlternativaNotFoundException!")
	void testGetByIdBuscaUmaAlternativaPorIdERetornaAlternativaNotFoundException() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenThrow(new AlternativaNotFoundException());

		// execucao
		try {
			alternativaServiceImpl.getById(anyLong());
			fail();
		} catch (Exception e) {
			// verificacao
			assertEquals(AlternativaNotFoundException.class, e.getClass());
		}
	}

//	@Test
//	void testPostListaAlternativa() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testPostListaAlternativasComQuestaoSalva() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("Cria uma alternativa")
	void testPostCriaUmaAlternativa() {
		// cenario
		when(alternativaRepository.save(any())).thenReturn(alternativa);
		when(questaoRepository.findById(anyLong())).thenReturn(questaoOptional);
		when(modelMapper.map(any(), eq(AlternativaDTO.class))).thenReturn(alternativaDTO);

		// execucao
		AlternativaDTO dto = alternativaServiceImpl.post(alternativaDTO);

		// verificacao
		assertAll("Alternativa",
				() -> assertNotNull(dto),
				() -> assertEquals(alternativaDTO.getClass(), dto.getClass()),
				() -> assertEquals(ID, dto.getId()),
				() -> assertEquals("", dto.getFoto())
//				() -> assertEquals(QUESTAO_ID, dto.getQuestaoId())
				);

	}

	@Test()
	@DisplayName("Ao criar uma alternativa retorna QuestaoNotFoundException!")
	void testAoCriaUmaAlternativaRetornaQuestaoNotFoundException() {
		// cenario
		when(questaoRepository.findById(anyLong())).thenThrow(QuestaoNotFoundException.class);

		// execucao
		// estrutura try catch para capturar o erro lancado
		try {

			alternativaServiceImpl.post(alternativaDTO);
			// verificacao
			verifyNoInteractions(alternativaRepository); // verifica se esse método não foi chamado
		} catch (Exception e) {
//			assertEquals(QuestaoNotFoundException.class, e.getClass());
		} // testar o parâmetro e, exemplo e.getClass, é optional

	}

	@Test
	@DisplayName("Deveria atualizar uma alternativa")
	void testPutAtualizaUmaAlternativa() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenReturn(alternativaOptional);
		when(alternativaRepository.save(any())).thenReturn(alternativa);

		// execucao
		AlternativaDTO a = alternativaServiceImpl.put(alternativaDTO);

		// verificacao
		assertNotNull(a);
	}

	@Test
	@DisplayName("Deveria ao atualizar uma alternativa que nao existe retornar AlternativaNotFoundException!")
	void testPutAoAtualizarUmaAlternativaDeveriaRetornarAlternativaNotFoundException() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenThrow(AlternativaNotFoundException.class);

		// execucao
		try {
			alternativaServiceImpl.put(alternativaDTO);
			verifyNoInteractions(alternativaRepository);
		} catch (Exception e) {
		}
	}

	@Test
	@DisplayName("Deveria deletar uma alternativa pelo id")
	void testDeleteDeletaUmaAlternativaPeloId() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenReturn(alternativaOptional);

		/**
		 * doNothing() NÃO faça nada when() QUANDO alternativaRepository.deleteById()
		 * for chamado
		 */
		doNothing().when(alternativaRepository).deleteById(anyLong()); // doNothing() usado para metodos que retornam
																		// void

		// execucao
		alternativaServiceImpl.deleteById(ID);

		/**
		 * verify() VERIFIQUE que o alternativaRepository foi chamado times() 1 VEZ para
		 * o método deleteById()
		 */
		// verificacao
		verify(alternativaRepository, times(1)).deleteById(anyLong());
	}

	@Test
	@DisplayName("Deveria ao deletar uma alternativa pelo id retornar AlternativaNotFoundException!")
	void testDeleteAoTentarDeletarUmaAlternativaPeloIdDeveriaRetornarAlternativaNotFoundException() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenThrow(AlternativaNotFoundException.class);

		// execucao
		try {
			alternativaServiceImpl.deleteById(alternativa.getId());
			verifyNoInteractions(alternativaRepository);
		} catch (Exception e) {
		}
	}

	/**
	 * Método criado para inicializar os objetos para os testes
	 */
	private void startCarga() {
		LOGGER.info("Criando carga!");
//		Usuario usuario1 = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", null, null, null);
//		CategoriaQuestao cq1 = new CategoriaQuestao(null, "Categoria questao teste 2", "Descrição categoria questao 2",
//				null);
//		Questao questao1 = new Questao(null, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
//				DificuldadeQuestao.FACIL, null, null, null, null);
		questaoOptional = Optional.of(new Questao(QUESTAO_ID, null, null, null, null, null, null, null, null, null));
		questaoOptional.get().setId(QUESTAO_ID);
//		alternativa = new Alternativa(ID, TEXTO_ALTERNATIVA, "", questaoOptional.get());
//		alternativaDTO = new AlternativaDTO(ID, TEXTO_ALTERNATIVA, "", QUESTAO_ID);
		alternativaDTO = new AlternativaDTO(ID, TEXTO_ALTERNATIVA, "");
//		alternativaOptional = Optional.of(new Alternativa(ID, TEXTO_ALTERNATIVA, "", questaoOptional.get()));
	}

}
