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

import br.com.pucminas.elderest.model.Medicamento;
import br.com.pucminas.elderest.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/medicamento")
@AllArgsConstructor
public class MedicamentoController {

    private static final String ADD_MEDICAMENTO = "medicamentos/add-medicamento";

    private final MedicamentoService service;

    @GetMapping(path = "/salvar")
    public String paginaAddMedicamento(@ModelAttribute final Medicamento medicamento, final Model model) {
        model.addAttribute("medicamento", medicamento);
        return ADD_MEDICAMENTO;
    }

    @PostMapping(path = "/salvar")
    public String saveMedicamento(@Valid @ModelAttribute final Medicamento medicamento, final Errors errors, final RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            return ADD_MEDICAMENTO;
        }
        log.info("Salvando medicamento: {}", medicamento);
        service.salvarMedicamento(medicamento);
        attributes.addFlashAttribute("mensagemCriado", "Medicamento criado com sucesso");

        return "redirect:/medicamento/listar";
    }

    @GetMapping(path = "medicamentos/atualizar")
    public String atualizarMedicamento(@RequestParam final Long medicamentoId, final Model model) {
        final Medicamento medicamento = service.getMedicamento(medicamentoId);
        model.addAttribute("medicamento", medicamento);
        return ADD_MEDICAMENTO;
    }

    @GetMapping(path = "/listar")
    public String getAllMedicamentos(final Model model) {
        log.info("Retornando todos os medicamentos");
        final List<Medicamento> medicamentos = service.getAllMedicamentos();
        model.addAttribute("medicamentos", medicamentos);
        return "medicamentos/listar-medicamento";
    }

    @GetMapping(path = "medicamentos/deletar")
    public String deleteMedicamento(@RequestParam final Long medicamentoId, final RedirectAttributes attributes) {
        log.info("Deletando o medicamento de id: {}", medicamentoId);
        service.deleteMedicamento(medicamentoId);
        attributes.addFlashAttribute("mensagemDeletado", "Medicamento deletado com sucesso");
        log.info("Deletado com sucesso");
        return "redirect:/medicamento/listar";
    }
}
