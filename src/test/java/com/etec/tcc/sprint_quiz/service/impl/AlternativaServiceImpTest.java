package com.etec.tcc.sprint_quiz.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
//import org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe de teste do Servico AlternativaService
 * 
 * @author hsjon
 * @date 26/12/2022
 */

/**
 * Garante um teste de unidade puro utilizando apenas o mockito e os mocks das
 * classes sem utilizar o spring tornando o teste mais rápido, leve e menos
 * custoso
 *
 */
@ExtendWith(MockitoExtension.class)
class AlternativaServiceImpTest {

	private static final long ID = 1L;
	private static final long QUESTAO_ID = 1L;
	private static final String TEXTO_ALTERNATIVA = "texto alternativa a";
	private static final Logger LOGGER = LoggerFactory.getLogger(AlternativaServiceImpTest.class);

	private Alternativa alternativa;
	private AlternativaDTO alternativaDTO;
	private Optional<Alternativa> alternativaOptional;
	private Optional<Questao> questaoOptional;
	private List<AlternativaDTO> listaDTO;
	private List<Alternativa> lista;

	/**
	 * dependencia da classe que sera testada InjectMocks -> cria uma instancia
	 * real, ja que é a classe que está sendo testada
	 */
	@InjectMocks
	private AlternativaServiceImp alternativaServiceImpl;

	/**
	 * dependencia do servico aqui ela sera mockada
	 * <hr>
	 * Mock -> cria um mock e nao a classe real, para personalizar as respostas
	 * dessa classe
	 */
//	@Mock
//	private Alternativa alternativa;

	/**
	 * dependencia do servico aqui ela sera mockada
	 * <hr>
	 * Mock -> cria um mock e nao a classe real, para personalizar as respostas
	 * dessa classe
	 */
	@Mock
	private AlternativaRepository alternativaRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 * <hr>
	 * Mock -> cria um mock e nao a classe real, para personalizar as respostas
	 * dessa classe
	 */
	@Mock
	private QuestaoRepository questaoRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 * <hr>
	 * Mock -> cria um mock e nao a classe real, para personalizar as respostas
	 * dessa classe
	 */
	@Mock
	private ObjectMapperUtils modelMapper;

	/**
	 * metodo utilizado para inicializar recursos antes de cada teste ser rodado
	 * <hr>
	 * BeforeEach -> indica que este metodo rodara antes de cada um dos testes
	 */
	@BeforeEach
	void setUp() {
		/**
		 * MockitoAnnotations.openMocks(this) ->
		 * <hr>
		 * inicializa os Mocks dessa classe, passando a própria classe, ele já
		 * inicializa todos os mocks necessários
		 */
		MockitoAnnotations.openMocks(this);
		startCarga(); // cria os objetos na memória para os testes
	}

	@Test
	@DisplayName("Busca Todas as Alternativas")
	void testGetAll_BuscaTodasAsAlternativas_RertonaListaComTodasExistentes() {
		// cenario
		when(alternativaRepository.findAll()).thenReturn(lista);
		when(modelMapper.mapAll(anyList(), eq(AlternativaDTO.class))).thenReturn(listaDTO);

		// execucao
		List<AlternativaDTO> dtos = alternativaServiceImpl.getAll();

		// verificaco

		assertAll("dtos", () -> assertNotNull(dtos),
				() -> assertEquals(alternativaDTO.getClass(), dtos.get(0).getClass()),
				() -> assertEquals(ID, dtos.get(0).getId()),
				() -> assertEquals(TEXTO_ALTERNATIVA, dtos.get(0).getTexto()),
				() -> assertEquals("", dtos.get(0).getFoto()),
				() -> assertEquals(Arrays.asList(alternativaDTO, alternativaDTO), dtos));
	}

	@Test
	@DisplayName("Busca todas as Alternativas filtradas por texto")
	void testeGetAllByTexto_BuscaTodasAsAlternativasFIltradasPorTexto_RetornaUmaListaDeAlternativasFiltradas() {
		// cenário
//		when(modelMapper.map(any(), any())).thenReturn(listDTO);
		when(alternativaRepository.findAllByTextoContainingIgnoreCase(TEXTO_ALTERNATIVA)).thenReturn(lista);

		// execucao
		// sut - System under test
		List<AlternativaDTO> sutLista = alternativaServiceImpl.getAllByTexto(TEXTO_ALTERNATIVA);

		// verificacao
		org.assertj.core.api.Assertions.assertThat(sutLista).isNotNull();
	}

	/**
	 * DisplayName("Nome") // define um nome personalizado para o método
	 */
	@Test
	@DisplayName("Busca uma alternativa pelo id")
	void testGetByIda_BuscaUmaAlternativaPorId_RetornaAlternativaEncontrada() {

		/**
		 * Quando o método findById() de AlternativaRepository for chamado passando
		 * qualquer Long, então retorne o que eu passar
		 */
		// cenário
		Mockito.when(alternativaRepository.findById(ID)).thenReturn(alternativaOptional);
		when(modelMapper.map(alternativa, AlternativaDTO.class)).thenReturn(alternativaDTO);

		/**
		 * Como o serviço já foi mockado é feita a chamada, como se fosse a classe real
		 */
		// execução
		AlternativaDTO response = alternativaServiceImpl.getById(ID);

		/**
		 * É feita a verificação comparando o que é esperado com a resposta
		 */
		// verificação
//		Assertions.assertNotNull(response); // sem import static
//		assertEquals(alternativaDTO.getClass(), response.getClass()); // compara dois objs, valores...
//		assertEquals(ID, response.getId());
//		assertEquals(TEXTO_ALTERNATIVA, response.getTexto());
		assertAll("response",
				() -> assertNotNull(response),
				() -> assertEquals(alternativaDTO.getClass(), response.getClass()),
				() -> assertEquals(ID, response.getId()),
				() -> assertEquals(TEXTO_ALTERNATIVA, response.getId()),
				() -> assertEquals("", response.getFoto()));

	}

	@Test
	@DisplayName("Busca uma alternativa pelo id e retorna AlternativaNotFoundException com o id buscado na mensagem")
	void testGetById_BuscaUmaAlternativaPorId_RetornaAlternativaNotFoundExceptionComIdNaMensagem() {

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
	void testGetById_BuscaUmaAlternativaPorId_RetornaAlternativaNotFoundException() {
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

	@Test
	@DisplayName("Deveria salvar uma lista de alternativas")
	void testPost_ListaAlternativa_RetornaAListaDeAlternativasSalvas() {
		//cenario
		when(modelMapper.mapAll(anyList(), eq(Alternativa.class))).thenReturn(lista);
		when(alternativaRepository.saveAll(anyList())).thenReturn(lista);
		when(modelMapper.mapAll(anyList(), eq(AlternativaDTO.class))).thenReturn(listaDTO);
		
		//execucao
		List<AlternativaDTO> dtos = alternativaServiceImpl.postListaAlternativa(listaDTO);
		
		//verificacoes
		assertAll("dtos", () ->assertNotNull(dtos));
	}

//	@Test
//	void testPostListaAlternativasComQuestaoSalva() {
//		fail("Not yet implemented");
//	}

	@Test
	@DisplayName("Cria uma alternativa")
	void testPost_CriaUmaAlternativa_RetornaAlternativaCriada() {
		// cenario
		when(modelMapper.map(any(), eq(Alternativa.class))).thenReturn(alternativa);// eq()usado para passar tipo de
																					// classe
		when(alternativaRepository.save(any())).thenReturn(alternativa);
		when(modelMapper.map(any(), eq(AlternativaDTO.class))).thenReturn(alternativaDTO);

		// execucao
		AlternativaDTO dto = alternativaServiceImpl.post(alternativaDTO);

		// verificacao
		assertAll("dto", () -> assertNotNull(dto), () -> assertEquals(alternativaDTO.getClass(), dto.getClass()),
				() -> assertEquals(ID, dto.getId()), () -> assertEquals(TEXTO_ALTERNATIVA, dto.getTexto()),
				() -> assertEquals("", dto.getFoto()));

	}

	@Test
	@DisplayName("Deveria atualizar uma alternativa")
	void testPut_AtualizaUmaAlternativa_RetornaAlternativaAtualizada() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenReturn(alternativaOptional);
		when(alternativaRepository.save(any())).thenReturn(alternativa);
		when(modelMapper.map(any(), any())).thenReturn(alternativaDTO);

		// execucao
		AlternativaDTO a = alternativaServiceImpl.put(alternativaDTO);

		// verificacao
		assertNotNull(a);
	}

	@Test
	@DisplayName("Deveria ao atualizar uma alternativa que nao existe retornar AlternativaNotFoundException!")
	void testPut_AoAtualizarUmaAlternativa_DeveriaRetornarAlternativaNotFoundException() {
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
	void testDelete_DeletaUmaAlternativaPeloId() {
		// cenario
		when(alternativaRepository.findById(anyLong())).thenReturn(alternativaOptional);

		/**
		 * doNothing() usado para metodos que retornam void
		 * <hr>
		 * doNothing() NÃO FAÇA nada
		 * <hr>
		 * when() QUANDO alternativaRepository.deleteById() for chamado
		 */
		doNothing().when(alternativaRepository).deleteById(anyLong());

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
	void testDelete_AoTentarDeletarUmaAlternativaPeloId_DeveriaRetornarAlternativaNotFoundException() {
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
//		LOGGER.info("Criando carga!");
//		Usuario usuario1 = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", null, null, null);
//		CategoriaQuestao cq1 = new CategoriaQuestao(null, "Categoria questao teste 2", "Descrição categoria questao 2",
//				null);
//		Questao questao1 = new Questao(null, "instituicao ", LocalDate.now(), "imagem", "Texto questão teste",
//				DificuldadeQuestao.FACIL, null, null, null, null);
		questaoOptional = Optional.of(new Questao(QUESTAO_ID, null, null, null, null, null, null, null, null, null));
		questaoOptional.get().setId(QUESTAO_ID);
		alternativa = new Alternativa(ID, TEXTO_ALTERNATIVA, "");
		alternativaDTO = new AlternativaDTO(ID, TEXTO_ALTERNATIVA, "");
		alternativaOptional = Optional.of(new Alternativa(new AlternativaDTO(ID, TEXTO_ALTERNATIVA, "")));
		listaDTO = List.of(alternativaDTO, alternativaDTO);
		lista = Arrays.asList(alternativa, alternativa);

	}

}
