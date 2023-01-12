package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // sobre um contexto da aplicação para teste
//@SpringBootTest
@AutoConfigureMockMvc//necessário para utilizar o MockMvc
(addFilters = false) // desabilita os filtros de segurança para rodar sem autenticação
//@WebMvcTest(AlternativaController.class)//não pode ser utilizado por conta da camada de segurança não ser carregaca. Carrega apenas a camada web
class AlternativaControllerTest {
	AlternativaDTO dto;

//	@Autowired
//	private WebApplicationContext webApplicationContext;

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
//	@InjectMocks // cria uma instancia real, ja que é a classe que está sendo testada
	@Autowired
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
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		startdb();

		/**
		 * Apaga todos os registros do banco de dados antes de iniciar os testes
		 */
		alternativaRepository.deleteAll();

	}
	
	@Test
	void testPost() throws Exception {
		//cenario
		URI uri = new URI("/alternativas");
		AlternativaDTO obj = new AlternativaDTO(1L,"texto post","");
		String json = mapper.writeValueAsString(obj);
		when(alternativaService.post(any(AlternativaDTO.class))).thenReturn(obj); //mockando a dependencia
		alternativaController.getById(1L);

		
//		String json = mapper.writeValueAsString(new AlternativaDTO(IDs, TEXTO_ALTERNATIVA_DTO, FOTO));
		mockMvc.perform(post(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json))

				.andDo(MockMvcResultHandlers.print()) //para exibir a resposta no console
				.andExpect(status().isCreated()).andExpect(content().contentType("application/json; charset=utf-8"))
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.texto").value("texto post"));
	}
	

	@Test
	void testGetById_DeveriaRetornarUmaAlternativaPorID() throws Exception {
		alternativaController.getById(1L);

		URI uri = new URI("/alternativas/1");
		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))

//				.andDo(MockMvcResultHandlers.print()) //para exibir a resposta no console
				.andExpect(status().isOk()).andExpect(content().contentType("application/json; charset=utf-8"))
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.texto").value("texto 1"));
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("Deveria Realizar Chamada Passando Id e Retornar Uma Alternativa By Id")
	void testGetById_DeveriaRealizarChamadaPassandoId_RetornaUmaAlternativaById() throws Exception {
		List<AlternativaDTO> listaDTO = List.of(new AlternativaDTO(1L, "texto 1", ""),
				new AlternativaDTO(1L, "texto 1", ""));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		ResponseEntity<List<AlternativaDTO>> response = new ResponseEntity<>(listaDTO, headers, HttpStatus.OK);
//		alternativaService.getAllByTexto("texto")).thenReturn(listaDTO);

		mockMvc.perform(get("/alternativas/texto").contentType("application/json; charset=utf-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json; charset=utf-8"))
				.andExpect(jsonPath("$.id").value("1"))
//				.andExpect(jsonPath("$").value(dto))
				.andExpect(jsonPath("$.texto").value("texto 1"));
	}

	@Test
	void testGetAllByTexto() {

	}

	@Test
	@DisplayName("Deveria Salvar Uma Alternativa e Retornar Alternativa Salva E Status Created 201")
	void testPost_DeveriaSalvarUmaAlternativa_RetornaAlternativaSalvaEStatusCreated201()
			throws JsonProcessingException, Exception {

		// cenario
		ResponseEntity<AlternativaDTO> entity1 = new ResponseEntity<>(dto, HttpStatus.CREATED);

		when(alternativaService.post(dto)).thenReturn(dto);
		when(alternativaController.post(dto)).thenReturn(entity1);

//		mockMvc.perform( MockMvcRequestBuilders
//			      .post("/alternativas")
//			      .content(mapper.writeValueAsString(new AlternativaDTO(1L, "texto ALternativaDTO", "")))
//			      .contentType(MediaType.APPLICATION_JSON)
//			      .accept(MediaType.APPLICATION_JSON))
//		      .andExpect(status().isCreated())
//		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

		// execucao
		URI uri = new URI("/alternativas");
		String json = mapper.writeValueAsString(dto);
		/**
		 * passando o método Post() pois é o que será testado
		 * 
		 * ele recebe a URI da requisicao
		 */
		mockMvc.perform(post(uri)

				// verificacao
				/**
				 * content() passamos o obj no corpo(body) da requisicao
				 */
				.content(json)

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
				.andExpect(jsonPath("$").value(dto)
//						jsonPath("$.texto").value(dto)
				);

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
//		ResponseEntity<AlternativaDTO> entity = new ResponseEntity<>(dto,HttpStatus.CREATED);
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