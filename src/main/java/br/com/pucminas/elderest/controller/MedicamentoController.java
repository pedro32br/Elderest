package br.com.pucminas.elderest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucminas.elderest.model.Medicamento;
import br.com.pucminas.elderest.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/medicamento")
@AllArgsConstructor
public class MedicamentoController {

    private final MedicamentoService service;

    @PostMapping
    public ResponseEntity<Medicamento> saveMedicamento(@RequestBody @Valid final Medicamento medicamento) {
        log.info("Salvando medicamento: {}", medicamento);
        return new ResponseEntity<>(service.salvarMedicamento(medicamento), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{nome}")
    @ResponseBody
    public ResponseEntity<List<Medicamento>> getMedicamento(@PathVariable final String nome) {
        log.info("Pegando informacoes de medicamentos");
        return new ResponseEntity<>(service.getMedicamento(nome), HttpStatus.OK);
    }

    @GetMapping(path = "")
    @ResponseBody
    public ResponseEntity<List<Medicamento>> getAllMedicamentos() {
        log.info("Retornando todos os medicamentos");
        return new ResponseEntity<>(service.getAllMedicamentos(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicamento(@PathVariable final String nome) {
        log.info("Deletando o medicamento: {}", nome);
        service.deleteMedicamento(nome);
        log.info("Deletado com sucesso");
    }
}
