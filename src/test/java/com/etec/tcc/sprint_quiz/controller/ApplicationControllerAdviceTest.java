package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ApiErrors;
import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.CategoriaQuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.ProvaNotFoundException;
import com.etec.tcc.sprint_quiz.exception.QuestaoNotFoundException;
import com.etec.tcc.sprint_quiz.exception.UsuarioNotFoundException;

class ApplicationControllerAdviceTest {

	private static final String CATEGORIA_DE_PROVA_NAO_ENCONTRADA = "Categoria de Prova não encontrada!";
	@InjectMocks
	private ApplicationControllerAdvice controller;

	@BeforeEach
	void setUp() throws Exception {
//		MockitoAnnotations.openMocks(this); //corrigir depois da mudança para o java 8
	}

	@Test
	void testHandleCategoriaProvaNotFoundExceptionDeveriaRetornarResponseEntityStatusNotFound() {
		ResponseEntity<ApiErrors> response = controller.handleCategoriaProvaNotFoundException(
				new CategoriaProvaNotFoundException(), new MockHttpServletRequest());
		
		

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals(CATEGORIA_DE_PROVA_NAO_ENCONTRADA, response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/categoriaProva/2", response.getBody().getPath());
//		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void testHandleCategoriaQuestaoNotFoundDeveriaRetornarResponseEntityStatusNotFound() {
		ResponseEntity<ApiErrors> response = controller.handleCategoriaQuestaoNotFoundException(
				new CategoriaQuestaoNotFoundException(), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals("Categoria da Questão não encontrada!", response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/categoriaQuestao/2", response.getBody().getPath());
//		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void testHandleProvaNotFoundException() {
		ResponseEntity<ApiErrors> response = controller.handleProvaNotFoundException(
				new ProvaNotFoundException(), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals("Prova não encontrada!", response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/prova/2", response.getBody().getPath());
//		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void testHandleQuestaoNotFoundException() {
		ResponseEntity<ApiErrors> response = controller.handleQuestaoNotFoundException(
				new QuestaoNotFoundException(), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals("Questão não encontrada!", response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/questao/2", response.getBody().getPath());
//		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void testHandleAlternativaNotFoundException() {
		ResponseEntity<ApiErrors> response = controller.handleAlternativaNotFoundException(
				new AlternativaNotFoundException(), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals("Alternativa não encontrada!", response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/alternativa/2", response.getBody().getPath());
//		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

	@Test
	void testHandleUsuarioNotFoundException() {
		ResponseEntity<ApiErrors> response = controller.handleUsuarioNotFoundException(
				new UsuarioNotFoundException(), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ApiErrors.class, response.getBody().getClass());
		assertEquals("Usuário não encontrado", response.getBody().getErrors());
		assertEquals(404, response.getBody().getStatus());
		assertNotEquals("/usuario/2", response.getBody().getPath());
		assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
	}

}
