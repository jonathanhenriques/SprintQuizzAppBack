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
import com.etec.tcc.sprint_quiz.model.Alternativa;
import com.etec.tcc.sprint_quiz.model.dto.AlternativaDTO;
import com.etec.tcc.sprint_quiz.repository.AlternativaRepository;
import com.etec.tcc.sprint_quiz.service.AlternativaService;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/alternativas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlternativaController {

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private AlternativaService alternativaService;
    
//    @Autowired
//	private ObjectMapperUtils objectMapperUtils;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TesteConfigBd.class);


    @Operation(summary = "Obtem todas as alternativas")
    @GetMapping
    public ResponseEntity<List<Alternativa>> getAll() { 
        return ResponseEntity.ok(alternativaService.getAll());
    }

//    @Operation(summary = "Obtem uma alternativa pelo id")
//    @GetMapping("/{id}")
//    public ResponseEntity<AlternativaDTO> getById(@PathVariable Long id) {
//    	
//    	Alternativa a = alternativaService.getById(id);
//    	return ResponseEntity.ok(modelMapper.map(a, AlternativaDTO.class));
////    	AlternativaDTO dto = modelMapper.map(a, AlternativaDTO.class);
////    	paraDTO(a);
////    	return ResponseEntity.ok(paraDTO(a));
////    	return ResponseEntity.ok(new AlternativaDTO(a));
////    	return ResponseEntity.ok(alternativaService.getById(id));
//    }
    
    
    
    @Operation(summary = "Obtem uma alternativa pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Alternativa> getById(@PathVariable Long id) {
    	return ResponseEntity.ok(alternativaService.getById(id));
    }
    
    
    
    @Operation(summary = "Obtem alternativas pelo texto da alternativa")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Alternativa>> getAllByTexto(@PathVariable String texto) {
    	return ResponseEntity.ok(alternativaService.getAllByTexto(texto));
    }

    @Operation(summary = "cria várias alternativas")
    @PostMapping("/listaAlternativas")
    public ResponseEntity<List<Alternativa>> postListaAlternativa(@Valid @RequestBody List<Alternativa> alternativas) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.postListaAlternativa(alternativas));
    }

    @Operation(summary = "cria uma nova alternativa")
    @PostMapping
    public ResponseEntity<AlternativaDTO> post(@Valid @RequestBody AlternativaDTO alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.post(alternativa));
    }

    @Operation(summary = "atualiza uma alternativa")
    @PutMapping
    public ResponseEntity<Alternativa> put(@Valid @RequestBody Alternativa alternativa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alternativaService.put(alternativa));
    }

    @Operation(summary = "deleta uma alternativa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	alternativaService.deleteById(id); 
    	return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/questaoId/{questaoId}/alternativAId/{alternativAId}")
    public void deleteAlternativa(@PathVariable Long questaoId,@PathVariable Long alternativAId) {
    	alternativaService.deletaAlternativaDeQuestao(questaoId, alternativAId);
    	LOGGER.info("excluindo relacionamento e alternativa...excluido!");
    }
    
    
}
