package br.com.pucminas.elderest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucminas.elderest.model.Acompanhamento;
import br.com.pucminas.elderest.service.AcompanhamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(value = "/acompanhamento")
public class AcompanhamentoController {

    private final AcompanhamentoService service;

    @PostMapping
    public ResponseEntity<Acompanhamento> saveAcompanhamento(@RequestBody @Valid final Acompanhamento acompanhamento) {
        log.info("Salvando acompanhamento: {}", acompanhamento);
        return new ResponseEntity<>(service.salvarAcompanhamento(acompanhamento), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Acompanhamento> getAcompanhamento(@PathVariable final Long id) {
        log.info("Retornando o acompanhamento: {}", id);
        return new ResponseEntity<>(service.getAcompanhamento(id), HttpStatus.OK);
    }
}
