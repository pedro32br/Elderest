package br.com.pucminas.elderest.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.pucminas.elderest.model.UsuarioDto;
import br.com.pucminas.elderest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/registrar")
@AllArgsConstructor
public class RegistrationController {

    private final UserService service;

    @ModelAttribute("usuario")
    public UsuarioDto userRegistrationDto() {
        return new UsuarioDto();
    }

    @GetMapping
    public String registerForm(@ModelAttribute("usuario") final UsuarioDto usuario) {
        return "login/registration";
    }

    @PostMapping
    public String registration(@Valid @ModelAttribute("usuario") final UsuarioDto usuario, final Errors errors) {
        if(errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            return "login/registration";
        }
        service.save(usuario);
        return "redirect:/login?success";
    }
}
