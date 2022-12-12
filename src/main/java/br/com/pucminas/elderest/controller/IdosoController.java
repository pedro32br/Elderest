package br.com.pucminas.elderest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.service.IdosoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/idoso")
@AllArgsConstructor
public class IdosoController {

    private final IdosoService service;

    @PostMapping
    public ResponseEntity<Idoso> saveIdoso(@RequestBody @Valid final Idoso idoso) {
        log.info("Salvando idoso: {}", idoso);
        return new ResponseEntity<>(service.salvarIdoso(idoso), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{cpf}")
    @ResponseBody
    public ResponseEntity<Idoso> getIdoso(@PathVariable final String cpf) {
        log.info("Pegando informacoes do idoso");
        return new ResponseEntity<>(service.getIdoso(cpf), HttpStatus.OK);
    }

    @GetMapping(path = "")
    @ResponseBody
    public ResponseEntity<List<Idoso>> getAllIdosos() {
        log.info("Retornando todos os idosos");
        return new ResponseEntity<>(service.getAllIdosos(), HttpStatus.OK);
    }

}
