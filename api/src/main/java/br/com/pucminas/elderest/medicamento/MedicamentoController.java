package br.com.pucminas.elderest.medicamento;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping(value = "/medicamento")
public class MedicamentoController {

    private static final Logger log = LoggerFactory.getLogger(MedicamentoController.class);

    private final MedicamentoServiceImpl service;

    public MedicamentoController(final MedicamentoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public MedicamentoDTO saveMedicamento(@RequestBody final MedicamentoDTO medicamento) {
        log.info("Salvando medicamento: {}", medicamento);
        return service.save(medicamento);
    }

    @GetMapping(path = "/{nome}")
    @ResponseBody
    @CrossOrigin
    public List<Medicamento> getMedicamento(@PathVariable final String nome) {
        log.info("Pegando informacoes de medicamentos");
        return service.getMedicamento(nome);
    }

    @GetMapping(path = "")
    @ResponseBody
    @CrossOrigin
    public List<Medicamento> getAllMedicamentos() {
        log.info("Retornando todos os medicamentos");
        return service.getAllMedicamentos();
    }

    @DeleteMapping(path = "/{nome}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    public void deleteMedicamento(@PathVariable final String nome) {
        log.info("Deletando o medicamento: {}", nome);
        service.deleteMedicamento(nome);
        log.info("Deletado com sucesso");
    }
}
