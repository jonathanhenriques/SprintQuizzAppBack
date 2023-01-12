package com.etec.tcc.sprint_quiz.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.etec.tcc.sprint_quiz.enums.DificuldadeQuestao;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.model.Prova;
import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.repository.ProvaRepository;
import com.etec.tcc.sprint_quiz.service.impl.ProvaServiceImp;

class ProvaControllerTest {

	@InjectMocks // injeta uma instacia real da classe
	private ProvaController controller;

	@Mock
	private ProvaRepository repository;

	@Mock
	private ProvaServiceImp service;

	private Prova prova;

	private Optional<Prova> optionalProva;

	private static final int INDEX = 0;
	private static final int DURACAO = 3;
	private static final String DESCRICAO = "teste";
	private static final String CATEGORIA_DE_PROVA_NAO_ENCONTRADA = "Categoria de Prova não encontrada!";
	private static final long ID = 1L;
	private static final String INSTITUICAO = "instituicao";
	private static final String NOME = "nome";
	private Questao questao;
	private Optional<Questao> optionalQuestao;
	private CategoriaProva categoriaProva;
	private Optional<CategoriaProva> optionalCategoriaProva;
	private Usuario usuario;
	private Optional<Usuario> optionalUsuario;
	

	@BeforeEach
	void setUp() {
//		MockitoAnnotations.openMocks(this); //corrigir depois da mudança para o java 8 erro
		startCategoriaProva();
	}

	@Test
	@DisplayName("GetAll Deveria Retornar Uma Lista De Prova")
	void testGetAllDeveriaRetornarUmaListaDeProva() {
		List<Prova> lista = new ArrayList<>();
		lista.add(prova);
		Mockito.when(service.getAll()).thenReturn(lista); // mockando resposta para chamada
		// do service

		ResponseEntity<List<Prova>> response = controller.getAll(); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
//		assertEquals(Prova.class, response.getBody().get(INDEX).getClass());
// verificações de atributos
		assertEquals(1, response.getBody().size());
		assertEquals(usuario, response.getBody().get(INDEX).getUsuario());
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(DURACAO, response.getBody().get(INDEX).getDuracao());
		assertEquals(NOME, response.getBody().get(INDEX).getNome());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(INSTITUICAO, response.getBody().get(INDEX).getInstituicao());
		assertEquals(null, response.getBody().get(INDEX).getCategoria());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
//		assertEquals(null, response.getBody().get(INDEX).getUsuario());

	}

	@Test
	void testGetByIdProvaDeveriaRetornarUmaProva() {
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(prova); // mockando resposta para chamada
		// do service

		ResponseEntity<Prova> response = controller.getByIdProva(ID); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Prova.class, response.getBody().getClass());
// verificações de atributos
		assertEquals(usuario, response.getBody().getUsuario());
		assertEquals(ID, response.getBody().getId());
		assertEquals(DURACAO, response.getBody().getDuracao());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(INSTITUICAO, response.getBody().getInstituicao());
		assertEquals(null, response.getBody().getCategoria());
		assertEquals(null, response.getBody().getQuestoes());
//		assertEquals(null, response.getBody().getUsuario());
	}

	@Test
	void testGetByCriadorIdDeveriaRetornarUmaListaDeProva() {
		List<Prova> lista = new ArrayList<>();
		lista.add(prova);
		Mockito.when(service.getAll()).thenReturn(lista); // mockando resposta para chamada
		// do service

		ResponseEntity<List<Prova>> response = controller.getAll(); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
//		assertEquals(Prova.class, response.getBody().get(INDEX).getClass());
// verificações de atributos
		assertEquals(1, response.getBody().size());
		assertEquals(usuario, response.getBody().get(INDEX).getUsuario());
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(DURACAO, response.getBody().get(INDEX).getDuracao());
		assertEquals(NOME, response.getBody().get(INDEX).getNome());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(INSTITUICAO, response.getBody().get(INDEX).getInstituicao());
		assertEquals(null, response.getBody().get(INDEX).getCategoria());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
//		assertEquals(null, response.getBody().get(INDEX).getUsuario());
	}

	@Test
	void testGetAllByNomeDeveriaRetornarUmaListaDeProva() {
		List<Prova> lista = new ArrayList<>();
		lista.add(prova);
		Mockito.when(service.getAll()).thenReturn(lista); // mockando resposta para chamada
		// do service

		ResponseEntity<List<Prova>> response = controller.getAll(); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
//		assertEquals(Prova.class, response.getBody().get(INDEX).getClass());
// verificações de atributos
		assertEquals(1, response.getBody().size());
		assertEquals(usuario, response.getBody().get(INDEX).getUsuario());
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(DURACAO, response.getBody().get(INDEX).getDuracao());
		assertEquals(NOME, response.getBody().get(INDEX).getNome());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(INSTITUICAO, response.getBody().get(INDEX).getInstituicao());
		assertEquals(null, response.getBody().get(INDEX).getCategoria());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
//		assertEquals(null, response.getBody().get(INDEX).getUsuario());
	}

	@Test
	void testGetAllByDescricaoDeveriaRetornarUmaListaDeProva() {
		List<Prova> lista = new ArrayList<>();
		lista.add(prova);
		Mockito.when(service.getAll()).thenReturn(lista); // mockando resposta para chamada
		// do service

		ResponseEntity<List<Prova>> response = controller.getAll(); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
//		assertEquals(Prova.class, response.getBody().get(INDEX).getClass());
// verificações de atributos
		assertEquals(1, response.getBody().size());
		assertEquals(usuario, response.getBody().get(INDEX).getUsuario());
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(DURACAO, response.getBody().get(INDEX).getDuracao());
		assertEquals(NOME, response.getBody().get(INDEX).getNome());
		assertEquals(DESCRICAO, response.getBody().get(INDEX).getDescricao());
		assertEquals(INSTITUICAO, response.getBody().get(INDEX).getInstituicao());
		assertEquals(null, response.getBody().get(INDEX).getCategoria());
		assertEquals(null, response.getBody().get(INDEX).getQuestoes());
//		assertEquals(null, response.getBody().get(INDEX).getUsuario());
	}

	@Test
	void testPostProvaDeveriaRetornarUmaProva() {
		Mockito.when(service.post(Mockito.any())).thenReturn(prova); // mockando resposta para chamada
		// do service

		ResponseEntity<Prova> response = controller.postProva(prova); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Prova.class, response.getBody().getClass());
// verificações de atributos
		assertEquals(usuario, response.getBody().getUsuario());
		assertEquals(ID, response.getBody().getId());
		assertEquals(DURACAO, response.getBody().getDuracao());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(INSTITUICAO, response.getBody().getInstituicao());
		assertEquals(null, response.getBody().getCategoria());
		assertEquals(null, response.getBody().getQuestoes());
//		assertEquals(null, response.getBody().getUsuario());
	}

	@Test
	void testPutProvaDeveriaRetornarUmaProva() {
		Mockito.when(service.post(Mockito.any())).thenReturn(prova); // mockando resposta para chamada
		// do service

		ResponseEntity<Prova> response = controller.postProva(prova); // efetuando a chamada

// verificações de tipos
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Prova.class, response.getBody().getClass());
// verificações de atributos
		assertEquals(usuario, response.getBody().getUsuario());
		assertEquals(ID, response.getBody().getId());
		assertEquals(DURACAO, response.getBody().getDuracao());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(DESCRICAO, response.getBody().getDescricao());
		assertEquals(INSTITUICAO, response.getBody().getInstituicao());
		assertEquals(null, response.getBody().getCategoria());
		assertEquals(null, response.getBody().getQuestoes());
//		assertEquals(null, response.getBody().getUsuario());
	}

	@Test
	void testDeleteProvaDeveriaRetornarNoContent() {
		Mockito.doNothing().when(service).delete(Mockito.anyLong());
		ResponseEntity<?> response = controller.deleteProva(ID);

		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}

	private void startCategoriaProva() {
//		 Usuario us = new Usuario(null, "Jonathan", "jonathan@email.com", "12345678", "", 0, null, null, new ArrayList<Questao>(), new ArrayList<Prova>());
		usuario = new Usuario(ID, NOME, "email@email.com", "12345678", "foto", Arrays.asList(), null, null);
//		optionalUsuario = Optional
//				.of(new Usuario(ID, NOME, "email@email.com", "12345678", "foto", Arrays.asList(), null, null));

		prova = new Prova(ID, NOME, DESCRICAO, DURACAO, usuario, null, INSTITUICAO, categoriaProva);
		optionalProva = Optional
				.of(prova = new Prova(ID, NOME, DESCRICAO, DURACAO, usuario, null, INSTITUICAO, categoriaProva));

		categoriaProva = new CategoriaProva(ID, "titulo", "descricao", null);
		optionalCategoriaProva = Optional.of(new CategoriaProva(ID, "titulo", "descricao", null));

		questao = new Questao(ID, INSTITUICAO, LocalDate.now(), "", "texto", DificuldadeQuestao.FACIL, null, null, null, usuario);
		optionalQuestao = Optional
				.of(new Questao(ID, INSTITUICAO, LocalDate.now(), "", "texto", DificuldadeQuestao.FACIL, null, null, null, usuario));

	}

}
