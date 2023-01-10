package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.etec.tcc.sprint_quiz.configuration.SecurityConfig;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // sobre um contexto da aplicação para teste
@AutoConfigureMockMvc(addFilters = false) // desabilita os filtros de segurança para rodar sem autenticação
//@WebMvcTest(AlternativaController.class)
class AlternativaControllerTest {
	AlternativaDTO dto;

	/**
	 * Injeta um objeto da Classe MockMvc, que é um cliente HTTP para interagir com
	 * o contexto web criado pelo spring
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Injeta um objeto da Classe ObjectMapper, responsável por transformar os objs
	 * em strings via jackson (JSON) para serem enviados na requisição
	 */
	@Autowired
	private ObjectMapper mapper;

	/**
	 * dependencia da classe que sera testada
	 */
	@InjectMocks // cria uma instancia real, ja que é a classe que está sendo testada
	private AlternativaController alternativaController;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@MockBean // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaRepository alternativaRepository;

	/**
	 * dependencia do servico aqui ela sera mockada
	 */
	@MockBean // cria um mock e nao a classe real, para personalizar as respostas dessa classe
	private AlternativaService alternativaService;

	@BeforeEach
	void setUp() throws Exception {

		startdb();

		/**
		 * Apaga todos os registros do banco de dados antes de iniciar os testes
		 */
		alternativaRepository.deleteAll();

	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById_DeveriaRealizarChamadaPassandoId_RetornaUmaAlternativaById() {
//		mockMvc.perform(null;)
	}

	@Test
	void testGetAllByTexto() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("Deveria Salvar Uma Alternativa e Retornar Alternativa Salva E Status Created 201")
	void testPost_DeveriaSalvarUmaAlternativa_RetornaAlternativaSalvaEStatusCreated201()
			throws JsonProcessingException, Exception {

		// cenario
		when(alternativaService.post(dto)).thenReturn(dto);

		// execucao
		/**
		 * passando o método Post() pois é o que será testado
		 * 
		 * ele recebe a URI da requisicao
		 */
		mockMvc.perform(post("/alternativas")
				// verificacao
				/**
				 * content() passamos o obj no corpo(body) da requisicao
				 */
				.content(mapper.writeValueAsString(dto))

				/**
				 * Indica o mediaType que o cliente está enviando ajuda a evitar erro 415
				 */
				.contentType(MediaType.APPLICATION_JSON)

				/**
				 * Indica o charset utilizado que o cliente está enviando
				 */

				.characterEncoding("utf-8"))

				/**
				 * Recebe um ResultMatcher usado para testar as informações da response (como
				 * status, body) Para realizar as verificações
				 */
				.andExpect(
						/**
						 * método status() usado para verificar código de status da resposta.
						 * 
						 * aqui é 201 -> Created
						 */
						status().isCreated())

				/**
				 * Aqui é verificada a expectativa de resposta que será retornada
				 * 
				 * Foi preciso pegar o conteúdo string(JSON) da response e transformar em obj
				 * usando o jsonPatch()
				 * 
				 * $ é usado para referenciar a raiz do JSON exemplo: obj e não exemplo:
				 * obj.propriedade
				 */
				.andExpect(jsonPath("$").value(dto));

	}

	@Test
	void testPut() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	void startdb() {

		final String FOTO = "";
		final String TEXTO_ALTERNATIVA_DTO = "texto alternativaDTO";
		final long IDs = 1L;
		dto = new AlternativaDTO(IDs, TEXTO_ALTERNATIVA_DTO, FOTO);

		/**
		 * Criar o usuário root para autenticar nos endpoints protegidos
		 */
//		usuarioService.cadastrarUsuario(new Usuario(0L, 
//			"Root", "root@root.com", "rootroot", " "));
	}

}
