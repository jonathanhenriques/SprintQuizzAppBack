package com.etec.tcc.sprint_quiz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.etec.tcc.sprint_quiz.exception.CategoriaProvaNotFoundException;
import com.etec.tcc.sprint_quiz.model.CategoriaProva;
import com.etec.tcc.sprint_quiz.repository.CategoriaProvaRepository;
import com.etec.tcc.sprint_quiz.service.impl.CategoriaProvaServiceImp;


@SpringBootTest
class CategoriaProvaServiceImpTest { 
	
	private static final int INDEX = 0;
	private static final String TITULO = "teste";
	private static final String DESCRICAO = "teste";
	private static final String CATEGORIA_DE_PROVA_NAO_ENCONTRADA = "Categoria de Prova não encontrada!";
	private static final long ID = 1L;

	@InjectMocks
	private CategoriaProvaServiceImp service;
	
	@Mock //indica que o atributo é um mock
	private CategoriaProvaRepository repository;
	//substitui essa declaração -> 	CategoriaProvaRepository repository = Mockito.mock(CategoriaProvaRepository.class);
	
	CategoriaProva categoriaProva;
	Optional<CategoriaProva> optionalCategoriaProva;
	
	
	@BeforeEach //executa antes de cada teste
	public void setUp(){//método que inicía configs necessárioas antes dos testes
		//inicializa os mocks necessários
//		MockitoAnnotations.openMocks(this); //this é a própria classe de teste
		startCategoriaProva();
		
	}
	
	
	


	@Test
	void testGetByIdDeveriaRetornarUmaCategoriaProvaPorId() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalCategoriaProva); //definindo o retorno do repository
		CategoriaProva response = service.getById(ID);
		
		assertNotNull(response);
		assertEquals(CategoriaProva.class, response.getClass());
		assertEquals(ID, response.getId());
	}
	
	@Test
	void testGetByIdDeveriaRetornarCategoriaNaoEncontrada() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new CategoriaProvaNotFoundException());//definindo o retorno do repository com erro
		
		try {
			service.getById(ID);
		} catch(Exception ex) {
			assertEquals(CategoriaProvaNotFoundException.class, ex.getClass());
			assertEquals(CATEGORIA_DE_PROVA_NAO_ENCONTRADA, ex.getMessage());
		}
	}

	@Test
	void testGetAllDeveriaRetornarUmaListaDeCategoriaProva() {
//		Mockito.when(repository.findAll()).thenReturn(List.of(categoriaProva));//definindo o retorno do repository //corrigir depois de mudança pra java 8 erro
		List<CategoriaProva> response = service.getAll(); //chamando o método da service
		
		assertNotNull(response); //verificando que a resposta não seja null
		assertEquals(1, response.size()); //verificando que a resposta tenha tamanho de 1
		assertEquals(CategoriaProva.class, response.get(INDEX).getClass()); //verificando que a resposta seja do tipo CategoriaProva
		assertEquals(ID, response.get(INDEX).getId()); //verificando que a resposta tenha o id esperado
		assertEquals(TITULO, response.get(INDEX).getTitulo()); //verificando que a resposta tenha o titulo esperado
		assertEquals(DESCRICAO, response.get(INDEX).getDescricao()); //verificando que a resposta tenha a descricao esperada
		assertEquals(null, response.get(INDEX).getProvas()); //verificando que a resposta do atributo provas da categoria seja null, pois nao foi passado
		
	}

	@Test
	void testPostCategoriaProvaDeveriaCriarUmaCategoriaProva() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(categoriaProva);//definindo o retorno do repository
		CategoriaProva response = service.post(categoriaProva); //chamando o método da service
		
		assertNotNull(response);
		assertEquals(CategoriaProva.class, response.getClass()); //verificando que a resposta seja do tipo CategoriaProva
		assertEquals(ID, response.getId()); //verificando que a resposta tenha o id esperado
		assertEquals(TITULO, response.getTitulo()); //verificando que a resposta tenha o titulo esperado
		assertEquals(DESCRICAO, response.getDescricao()); //verificando que a resposta tenha a descricao esperada
		assertEquals(null, response.getProvas()); //verificando que a resposta do atributo provas da categoria seja null, pois nao foi passado
	}
	
//	@Test ////ainda não implementado
//	void testPostCategoriaProvaDeveriaRetornarCategoriaProvaJaCadastrada() {
//		Mockito.when(repository.save(Mockito.any())).thenThrow(new CategoriaProvaNotFoundException()); //definindo o retorno do repository com erro
//		
//		try {
//			service.postCategoriaProva(categoriaProva);
//		} catch(Exception ex) {
//			assertEquals(CategoriaProvaNotFoundException.class, ex.getClass());
//			assertEquals(CATEGORIA_DE_PROVA_NÃO_ENCONTRADA, ex.getMessage());
//		}
//	} 
	
	@Test
	void testPutCategoriaProvaDeveriaRetornarUmaCategoriaProvaAtualizada() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(categoriaProva);//definindo o retorno do repository
		CategoriaProva response = service.post(categoriaProva); //chamando o método da service
		
		assertNotNull(response);
		assertEquals(CategoriaProva.class, response.getClass());
	}
	
	@Test
	void testPutCategoriaProvaDeveriaRetornarCategoriaProvaNaoEncontradaComParametro() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalCategoriaProva); //definindo o retorno do repository com erro
		
		try {
			optionalCategoriaProva.get().setId(2L);
			service.put(optionalCategoriaProva.get());
		} catch(Exception ex) {
			assertEquals(CategoriaProvaNotFoundException.class, ex.getClass());
			assertEquals("Categoria de Prova não encontrada! | " + 2, ex.getMessage()); 
		}
	}

	


	@Test
	void testDeleteCategoriaProvaDeveriaDeletarUmaCategoria() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalCategoriaProva);
		Mockito.doNothing().when(repository).deleteById(Mockito.anyLong()); //não faça nada quando o deleteById for chamado no repository
		service.delete(ID);
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong()); //verifica quantas vezes o método foi chamado, já que ele não tem retorno
	}
	
	@Test
	void testDeleteCategoriaProvaDeveriaRetornarCategoriaProvaNotFound() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new CategoriaProvaNotFoundException());
		
		try {
			service.delete(ID);
		}catch(Exception ex) {
			assertEquals(CategoriaProvaNotFoundException.class, ex.getClass());
			assertEquals(CATEGORIA_DE_PROVA_NAO_ENCONTRADA, ex.getMessage());
		}
	}
	
	
	private void startCategoriaProva() {
		 categoriaProva = new CategoriaProva(ID, TITULO, DESCRICAO, null );
		 optionalCategoriaProva = Optional.of(new CategoriaProva(ID, TITULO, DESCRICAO, null ));
		 
		 
	}
	

//	private List<CategoriaProva> criaListaDecategoriaProva(){
//		List<CategoriaProva> lista = new ArrayList<>();
//		CategoriaProva cp = new CategoriaProva(ID, "teste,", "teste", null );
//		CategoriaProva cp2 = new CategoriaProva(ID, "teste2,", "teste2", null );
//		
//		lista.add(cp);
//		lista.add(cp2);
//		return lista;
//	}

}
