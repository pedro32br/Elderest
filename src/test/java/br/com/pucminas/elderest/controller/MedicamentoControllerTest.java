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

import br.com.pucminas.elderest.model.Medicamento;
import br.com.pucminas.elderest.service.MedicamentoService;

@WebMvcTest(value = MedicamentoController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class MedicamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MedicamentoService service;

    @Test
    @DisplayName("Status 201 - Medicamento está correto")
    void Should_Success_Ok_When_Medicamento_Is_Right() throws Exception {
        final Medicamento med = new Medicamento("nome", 1, "substancia");
        when(service.salvarMedicamento(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento")
                        .content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Status 400 - Medicamento esta com nome nulo")
    void Should_Success_Ok_When_Nome_Is_Null() throws Exception {
        final Medicamento med = new Medicamento(null, 1, "substancia");
        when(service.salvarMedicamento(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento")
                        .content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Status 400 - Medicamento esta com quantidade negativa")
    void Should_Success_Ok_When_Quantidade_Negativa() throws Exception {
        final Medicamento med = new Medicamento("erro", -1, "substancia");
        when(service.salvarMedicamento(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento")
                        .content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Status 400 - Medicamento esta com substancia nula")
    void Should_Success_Ok_When_Substancia_Nula() throws Exception {
        final Medicamento med = new Medicamento("erro", 5, null);
        when(service.salvarMedicamento(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento")
                        .content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Status 400 - Medicamento esta com substancia vazia")
    void Should_Success_Ok_When_Substancia_Blank() throws Exception {
        final Medicamento med = new Medicamento("erro", 5, "");
        when(service.salvarMedicamento(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento")
                        .content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
