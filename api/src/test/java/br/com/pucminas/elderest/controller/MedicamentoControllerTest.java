package br.com.pucminas.elderest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pucminas.elderest.medicamento.MedicamentoController;
import br.com.pucminas.elderest.medicamento.MedicamentoDTO;
import br.com.pucminas.elderest.medicamento.MedicamentoServiceImpl;

@WebMvcTest(MedicamentoController.class)
class MedicamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private MedicamentoServiceImpl service;

    @Test
    @DisplayName("Status 201 - MedicamentoDTO is right")
    void Should_Success_Ok_When_Medicamento_Dto_Is_Right() throws Exception {
        final MedicamentoDTO med = new MedicamentoDTO(1L, "nome", 1, "substancia");
        when(service.save(any())).thenReturn(med);

        mockMvc.perform(post("/medicamento").content(mapper.writeValueAsString(med))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
