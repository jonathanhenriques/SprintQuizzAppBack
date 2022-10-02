package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.service.CategoriaProvaServiceImp;

@SpringBootTest
class CategoriaProvaControllerTest {
	
	@InjectMocks //injeta uma instacia real da classe
	private CategoriaProvaController controller;
	
	@Mock
	private CategoriaProvaRepository repository;
	
	@Mock
	private CategoriaProvaServiceImp service;

	private CategoriaProva categoriaProva;

	private Optional<CategoriaProva> optionalCategoriaProva;
	
	private static final int INDEX = 0;
	private static final String TITULO = "teste";
	private static final String DESCRICAO = "teste";
	private static final String CATEGORIA_DE_PROVA_NAO_ENCONTRADA = "Categoria de Prova não encontrada!";
	private static final long ID = 1L;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this); //inicializa as anotações dessa classe
		startCategoriaProva(); //cria as instancias de categoria que são usadas nos testes
	}

	@Test
	void testGetByIdDeveriaRetornarUmaCategoriaProva() {
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(categoriaProva); //mockando resposta para chamada do service
		
		ResponseEntity<CategoriaProva> response = controller.getById(ID);  //efetuando a chamada
		
		//verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CategoriaProva.class, response.getBody().getClass());
		//verificações de atributos
		assertEquals(ID, response.getBody().getId());
		assertEquals(TITULO, response.getBody().getTitulo());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(null, response.getBody().getProvas());
		
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testPostCategoriaProva() {
		fail("Not yet implemented");
	}

	@Test
	void testPutCategoriaProva() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletetaoCategoriaProva() {
		fail("Not yet implemented");
	}
	
	
	private void startCategoriaProva() {
		 categoriaProva = new CategoriaProva(ID, TITULO, DESCRICAO, null );
		 optionalCategoriaProva = Optional.of(new CategoriaProva(ID, TITULO, DESCRICAO, null ));
		 
		 
	}

}
