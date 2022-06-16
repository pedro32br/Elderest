package br.com.pucminas.elderest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucminas.elderest.dto.MedicamentoDTO;
import br.com.pucminas.elderest.entity.Medicamento;
import br.com.pucminas.elderest.service.MedicamentoService;

@RestController
@RequestMapping(value = "/medicamento")
public class MedicamentoController {

    private static final Logger log = LoggerFactory.getLogger(MedicamentoController.class);

    private final MedicamentoService service;

    public MedicamentoController(final MedicamentoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public MedicamentoDTO saveMedicamento(@RequestBody final MedicamentoDTO medicamento) {
        log.info("Salvando medicamento: {}", medicamento);
        return service.save(medicamento);
    }

    @GetMapping(path = { "", "/{id}" })
    @ResponseBody
    @CrossOrigin
    public List<Medicamento> getMedicamento(@PathVariable(required = false) final String id) {
        log.info("Pegando informacoes de medicamentos");
        return service.getMedicamento(id);
    }
}
