package br.com.pucminas.elderest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
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

import br.com.pucminas.elderest.model.Acompanhamento;
import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.service.AcompanhamentoService;

@WebMvcTest(value = AcompanhamentoController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class AcompanhamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private AcompanhamentoService service;

    private Acompanhamento acompanhamento;

    @BeforeEach
    void setup() {
        final Idoso idoso = new Idoso("Genivaldo", "248.794.400-51", 62);
        idoso.setId(1L);
        idoso.setDataInternacao(LocalDate.now());
        acompanhamento = new Acompanhamento("Pedro enfermeiro", "Paciente situacao normal"
                , 12, 9, 100, 37.1, 80, 100, 30, 2.24, idoso);
    }

    @Test
    @DisplayName("Status 400 - bad request")
    void Should_Success_Ok_When_Idoso_Is_Null() throws Exception {
        acompanhamento.setIdoso(null);
        when(service.salvarAcompanhamento(acompanhamento)).thenReturn(acompanhamento);

        mockMvc.perform(post("/acompanhamento")
                        .content(mapper.writeValueAsString(acompanhamento))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
