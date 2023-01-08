package com.etec.tcc.sprint_quiz.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.etec.tcc.sprint_quiz.model.Questao;
import com.etec.tcc.sprint_quiz.model.dto.QuestaoDTO;
import com.etec.tcc.sprint_quiz.repository.QuestaoRepository;
import com.etec.tcc.sprint_quiz.service.QuestaoService;
import com.etec.tcc.sprint_quiz.util.ObjectMapperUtils;

import io.swagger.v3.oas.annotations.Operation;


@RestController()
@RequestMapping("/questoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestaoController {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private QuestaoService questaoService;
    
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Operation(summary = "Obtem todas as questoes")
    @GetMapping
    public ResponseEntity<List<Questao>> getAll() {
        return ResponseEntity.ok(questaoRepository.findAll());
    }

    @Operation(summary = "Obtem questoes por id")
    @GetMapping("/{id}")
    public ResponseEntity<Questao> getById(@PathVariable Long id) {
    	return ResponseEntity.ok(questaoService.getById(id));
    }

    @Operation(summary = "Obtem questoes pelo texto da questao")
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Questao>> getAllByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(questaoService.getAllByTexto(texto));
    }

    @Operation(summary = "Obtem questoes  pela instituicao da questao")
    @GetMapping("/instituicao/{instituicao}")
    public ResponseEntity<List<Questao>> getAllByInstituicao(@PathVariable String instituicao) {
        return ResponseEntity.ok(questaoService.getAllByInstituicao(instituicao));
    }

    @Operation(summary = "Obtem questoes  pelo ano da questao")
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Questao>> findAllByAno(@PathVariable
                                                      @DateTimeFormat(
                                                              iso = DateTimeFormat.ISO.DATE)
                                                              LocalDate ano) {
        return ResponseEntity.ok(questaoService.findAllByAno(ano));
    }

    @Operation(summary = "Obtem questoes entre um periodo inicial e final")
    @GetMapping("/ano/entre/{anoInicial}/{anoFinal}")
    public ResponseEntity<List<Questao>> findAllByAnoInicialFinal(@PathVariable LocalDate anoInicial, LocalDate anoFinal) {
        return ResponseEntity.ok(questaoService.findAllByAnoInicialFinal(anoInicial, anoFinal));
    }

    @Operation(summary = "Obtem questoes pelo anteriores a um periodo")
    @GetMapping("/ano/antes/{ano}")
    public ResponseEntity<List<Questao>> findAllByAntesAno(@PathVariable @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
            LocalDate ano) {
        return ResponseEntity.ok(questaoService.findAllByAntesAno(ano));
    }

    @Operation(summary = "Obtem questoes pelo id do criador da questão")
    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Questao>> getQuestoesByCriadorId(@PathVariable Long id){
//        return questaoService.getQuestoesByCriadorId(id);
        return ResponseEntity.ok(questaoRepository.findAllByCriadorId(id));    }


    @Operation(summary = "Cadastra uma questao")
    @PostMapping
    public ResponseEntity<QuestaoDTO> postQuestao(@Valid @RequestBody
//                                                       @DateTimeFormat(
//                                                                  iso = DateTimeFormat.ISO.DATE)
                                                       QuestaoDTO questaoDTO) {
    	
        return ResponseEntity.status(HttpStatus.CREATED).body(questaoService.postQuestao(questaoDTO));
    }

    @Operation(summary = "Cadastra uma questao com alternativas")
    @PutMapping("/questaoComAlternativas")
    public ResponseEntity<Questao> postQuestaoComAlternativas(@RequestBody Questao questao){
        return ResponseEntity.ok(questaoService.salvarQuestaoComAlternativa(questao));
    }

    @Operation(summary = "Atualiza uma questao")
    @PutMapping
    public ResponseEntity<QuestaoDTO> putQuestao(@Valid @RequestBody QuestaoDTO questao) {
        return ResponseEntity.ok(questaoService.putQuestao(questao));
    }

    @Operation(summary = "Deleta uma questao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestao(@PathVariable Long id) {
    	questaoService.deleteQuestao(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
