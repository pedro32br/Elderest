package br.com.pucminas.elderest.acompanhamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/acompanhamento")
public class AcompanhamentoController {

    private static final Logger log = LoggerFactory.getLogger(AcompanhamentoController.class);

    private final AcompanhamentoServiceImpl service;

    public AcompanhamentoController(final AcompanhamentoServiceImpl service) {
        this.service = service;
    }

    //    @PostMapping
    //    @ResponseStatus(HttpStatus.CREATED)
    //    @CrossOrigin
    //    public AcompanhamentoDTO saveAcompanhamento(@RequestBody final AcompanhamentoDTO acompanhamentoDTO) {
    //        log.info("Salvando acompanhamento: {}", acompanhamentoDTO);
    //        return service.save(acompanhamentoDTO);
    //    }
    //
    //    @GetMapping(path = "/{id}")
    //    @ResponseBody
    //    @CrossOrigin
    //    public List<Medicamento> getMedicamento(@PathVariable final String id) {
    //        log.info("Pegando informacoes de medicamentos");
    //        return service.getMedicamento(id);
    //    }
    //
    //    @GetMapping(path = "")
    //    @ResponseBody
    //    @CrossOrigin
    //    public List<Medicamento> getAllMedicamentos() {
    //        log.info("Retornando todos os medicamentos");
    //        return service.getAllMedicamentos();
    //    }
}
