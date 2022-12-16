package br.com.pucminas.elderest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pucminas.elderest.model.Acompanhamento;
import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.service.AcompanhamentoService;
import br.com.pucminas.elderest.service.IdosoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/idoso")
@AllArgsConstructor
public class IdosoController {

    private static final String ADD_IDOSO = "idosos/add-idoso";
    private static final String ADD_ACOMPANHAMENTO = "idosos/add-acompanhamento";

    private final AcompanhamentoService acompanhamentoService;
    private final IdosoService service;

    @GetMapping(path = "/salvar")
    public String paginaAddIdoso(@ModelAttribute final Idoso idoso, final Model model) {
        model.addAttribute("idoso", idoso);
        return ADD_IDOSO;
    }

    @PostMapping(path = "/salvar")
    public String saveIdoso(@ModelAttribute @Valid final Idoso idoso, final Errors errors, final RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            return ADD_IDOSO;
        }
        log.info("Salvando idoso: {}", idoso);
        service.salvarIdoso(idoso);
        attributes.addFlashAttribute("mensagemCriado", "Idoso adicionado com sucesso");

        return "redirect:/idoso/listar";
    }

    @GetMapping(path = "/listar")
    public String getAllIdosos(final Model model) {
        log.info("Retornando todos os idosos");
        final List<Idoso> idosos = service.getAllIdosos();
        model.addAttribute("idosos", idosos);
        return "idosos/listar-idoso";
    }

    @GetMapping(path = "/idosos/deletar")
    public String deleteIdoso(@RequestParam final Long idosoId, final RedirectAttributes attributes) {
        log.info("Deletando idoso de id: {}", idosoId);
        service.deleteIdoso(idosoId);
        attributes.addFlashAttribute("mensagemDeletado", "Medicamento deletado com sucesso");
        log.info("Deletado com sucesso");
        return "redirect:/idoso/listar";
    }

    @GetMapping(path = "acompanhamento/adicionar")
    public String paginaAcompanhamento(@RequestParam final Long idosoId, final Model model) {
        final Idoso idoso = service.getIdoso(idosoId);
        model.addAttribute("idoso", idoso);
        final Acompanhamento acompanhamento = new Acompanhamento();
        acompanhamento.setIdoso(idoso);
        model.addAttribute("acompanhamento", acompanhamento);
        return ADD_ACOMPANHAMENTO;
    }

    @GetMapping(path = "acompanhamento/listar")
    public String paginaAcompanhamentoLista(@RequestParam final Long idosoId, final Model model) {
        final Idoso idoso = service.getIdoso(idosoId);
        model.addAttribute("idoso", idoso);
        return "idosos/listar-acompanhamento";
    }

    @PostMapping(path = "acompanhamento/adicionar")
    public String adicionarAcompanhamento(@ModelAttribute @Valid final Acompanhamento acompanhamento, final Errors errors,
            final RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            return ADD_IDOSO;
        }
        log.info("Salvando acompanhamento: {}", acompanhamento);
        acompanhamentoService.salvarAcompanhamento(acompanhamento);
        attributes.addFlashAttribute("mensagemCriado", "Acompanhamento adicionado com sucesso");

        return "redirect:/idoso/listar";
    }

}
