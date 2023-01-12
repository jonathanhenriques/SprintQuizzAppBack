package com.etec.tcc.sprint_quiz.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.etec.tcc.sprint_quiz.model.Prova;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProvaRepositoryTest {

	private static final String DESCRICAO = "Vestibular da fuvest 2019";
	private static final String NOME = "Fuvest";
	@Autowired
	private ProvaRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() throws Exception {
//		Usuario usuario = new Usuario(1L, "jonathan", "joanthan@email.com", "12345678", "", Arrays.asList(), null, null);
//		usuarioRepository.save(usuario);
//		repository.save(new Prova(0L, NOME, DESCRICAO, 6, usuario, null, "instituicao", null));

	}

	@Test
	void testFindAllByNomeContainingIgnoreCase() {
		List<Prova> lista = repository.findAllByNomeContainingIgnoreCase(NOME);

		assertEquals(NOME, lista.get(0).getNome());
	}

	@Test
	@DisplayName("Retorna lista filtrada por descricao")
	void testFindAllByDescricaoContainingIgnoreCaseDeveriaRetornarUmaListaFiltradaPorDescricao() {
		List<Prova> lista = repository.findAllByDescricaoContainingIgnoreCase(DESCRICAO);

		assertEquals(DESCRICAO, lista.get(0).getDescricao());
	}

	@Test
	@DisplayName("Retorna lista filtrada por id do usuario")
	void testFindAllByUsuarioIdDeveriaRetornarUmaListaFiltradaPorIdDoUsuario() {
		List<Prova> lista = repository.findAllByUsuarioId(1L);

		assertEquals(DESCRICAO, lista.get(0).getDescricao());
	}

}
