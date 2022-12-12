package br.com.pucminas.elderest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.service.IdosoService;

@WebMvcTest(value = IdosoController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class IdosoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private IdosoService service;

    @Test
    @DisplayName("Status 201")
    void Should_Success_Ok_When_Idoso_Is_Right() throws Exception {
        final Idoso idoso = new Idoso("Genivaldo", "248.794.400-51", 62);
        when(service.salvarIdoso(any())).thenReturn(idoso);

        mockMvc.perform(post("/idoso")
                        .content(mapper.writeValueAsString(idoso))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Status 400 - Idoso esta com nome nulo")
    void Should_Success_Fail_When_Nome_Is_Null() throws Exception {
        final Idoso idoso = new Idoso();
        when(service.salvarIdoso(any())).thenReturn(idoso);

        mockMvc.perform(post("/idoso")
                        .content(mapper.writeValueAsString(idoso))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Status 400 - Idoso esta com internando sendo novo demais")
    void Should_Success_Fail_When_He_Is_Too_Young() throws Exception {
        final Idoso idoso = new Idoso("Genivaldo", "248.794.400-51", 55);
        when(service.salvarIdoso(any())).thenReturn(idoso);

        mockMvc.perform(post("/idoso")
                        .content(mapper.writeValueAsString(idoso))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Status 400 - Idoso esta com internando com cpf errado")
    void Should_Success_Fail_When_Cpf_Is_Invalid() throws Exception {
        final Idoso idoso = new Idoso("Genivaldo", "248.794.400-50", 60);
        when(service.salvarIdoso(any())).thenReturn(idoso);

        mockMvc.perform(post("/idoso")
                        .content(mapper.writeValueAsString(idoso))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
