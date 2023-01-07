package com.etec.tcc.sprint_quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.tcc.sprint_quiz.configuration.TesteConfigBd;
import com.etec.tcc.sprint_quiz.exception.AlternativaNotFoundException;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.service.AlternativaService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jonathan
 * @date 03/01/2023
 *
 */
@Slf4j
@RestController
@RequestMapping("/alternativas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlternativaController {

	@Autowired
	private AlternativaService alternativaService;

//    @Autowired
//	private ObjectMapperUtils objectMapperUtils;

	private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);

	/**
	 * Método que busca Todas as Alternativas.
	 * 
	 * @return ResponseEntity com o objeto <code><List<AlternativaDTO></code> e o
	 *         HTTP status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 */
	@Operation(summary = "Obtem todas as alternativas")
	@GetMapping
	public ResponseEntity<List<AlternativaDTO>> getAll() {
		return ResponseEntity.ok(alternativaService.getAll());
	}

	/**
	 * Método que busca uma Alternativa pelo Id.
	 * 
	 * @param id - id da Alternativa
	 * 
	 * @return ResponseEntity com o objeto <code><AlternativaDTO></code> e o HTTP
	 *         status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 * 
	 * @throws AlternativaNotFoundException
	 */
	@Operation(summary = "Obtem uma alternativa pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<AlternativaDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(alternativaService.getById(id));
	}

	/**
	 * Método que busca uma ou mais Alternativa por texto
	 * 
	 * @param texto - texto da Alternativa
	 * 
	 * @return ResponseEntity com o objeto <code>List<AlternativaDTO></code> e o
	 *         HTTP status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 500 - Server Errors: Erro na
	 *         Aplicação!.
	 * 
	 */
	@Operation(summary = "Obtem alternativas pelo texto da alternativa")
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<AlternativaDTO>> getAllByTexto(@PathVariable String texto) {
		return ResponseEntity.ok(alternativaService.getAllByTexto(texto));
	}

//    @Operation(summary = "cria várias alternativas")
//    @PostMapping("/listaAlternativas")
//    public ResponseEntity<List<AlternativaDTO>> postListaAlternativa(@Valid @RequestBody List<Alternativa> alternativas) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.postListaAlternativa(alternativas));
//    }

	/**
	 * Método que cria uma Alternativa.
	 * 
	 * @param alternativa - alternativa a ser salva
	 * 
	 * @return ResponseEntity com o objeto <code><AlternativaDTO></code> e o HTTP
	 *         status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 * 
	 */
	@Operation(summary = "cria uma nova alternativa")
	@PostMapping
	public ResponseEntity<AlternativaDTO> post(@Valid @RequestBody AlternativaDTO alternativa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.post(alternativa));
	}

	/**
	 * Método que atualiza uma Alternativa.
	 * 
	 * @param alternativa - Alternativa a ser atualizada
	 * 
	 * @return ResponseEntity com o objeto <code><AlternativaDTO></code> e o HTTP
	 *         status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 * 
	 * @throws AlternativaNotFoundException
	 */
	@Operation(summary = "atualiza uma alternativa")
	@PutMapping
	public ResponseEntity<AlternativaDTO> put(@Valid @RequestBody AlternativaDTO alternativa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.put(alternativa));
	}

	/**
	 * Método que deleta uma Alternativa pelo Id.
	 * 
	 * @param id - id da Alternativa
	 * 
	 * @return ResponseEntity com o HTTP status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 * 
	 * @throws AlternativaNotFoundException
	 */
	@Operation(summary = "deleta uma alternativa pelo id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		alternativaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Método que remove e deleta uma Alternativa da Questao.
	 * 
	 * @param id - id da Alternativa a ser removida
	 * @param id - id da Questao com a lista
	 * @return ResponseEntity com o HTTP status
	 * 
	 *         HTTP Status:
	 * 
	 *         200 - OK: Sucesso. 400 - Bad Request: Erro na Requisição!. 401 -
	 *         unauthorized: Acesso Não Autorizado!. 404 - Not Found: Objeto Não
	 *         Encontrado!. 500 - Server Errors: Erro na Aplicação!.
	 * 
	 * @throws AlternativaNotFoundException
	 */
	@DeleteMapping("/questaoId/{questaoId}/alternativAId/{alternativAId}")
	public ResponseEntity<?> deleteAlternativa(@PathVariable Long questaoId, @PathVariable Long alternativAId) {
		alternativaService.removeAlternativaDeQuestao(questaoId, alternativAId);
		LOGGER.info("excluindo relacionamento e alternativa...excluido!");
		return ResponseEntity.noContent().build();
	}

}
