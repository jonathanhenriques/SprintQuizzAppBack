package com.etec.tcc.sprint_quiz.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlternativaRepositoryTest {

	@Autowired
	private AlternativaRepository repository;
	
	@BeforeAll
	 void start() throws Exception {
//		repository.deleteAll();
		repository.save(new Alternativa(0L,"Verde", "")); //com questao
	}

	@AfterAll
	 void end() throws Exception {
//		repository.deleteAll();
	}

	@Test
	@DisplayName("Retorna uma lista filtrada por texto")
	void testFindAllByTextoContainingIgnoreCaseDeveriaRetornarUmaListaDeAlternativaFiltradaPorTexto() {
		List<Alternativa> lista = repository.findAllByTextoContainingIgnoreCase("Verde");
		assertEquals("Verde", lista.get(0).getTexto());
		assertEquals(3, lista.size());
	}

}
