package br.com.pucminas.elderest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.repository.IdosoRepository;

@ExtendWith(MockitoExtension.class)
class IdosoServiceTest {

    private static final String CPF_FORMATADO = "24879440051";

    @InjectMocks
    private IdosoService service;

    @Mock
    private IdosoRepository repository;

    Idoso idoso;

    @BeforeEach
    void beforeEach() {
        idoso = new Idoso("Genivaldo", "248.794.400-51", 62);
    }

    @Test
    void Should_Save_Success_When_Send_Ok_Model() {
        when(repository.findByCpf(CPF_FORMATADO)).thenReturn(Optional.empty());
        final Idoso idosoNovo = idoso;
        idosoNovo.setId(1L);
        when(repository.save(idoso)).thenReturn(idosoNovo);

        final Idoso result = service.salvarIdoso(idoso);

        assertEquals("Genivaldo", result.getNome());
        assertEquals(1L, result.getId());
    }

    @Test
    void Should_Update_Success_When_Send_Same_Model() {
        final Idoso idosoNovo = idoso;
        idosoNovo.setId(1L);

        when(repository.findByCpf(CPF_FORMATADO)).thenReturn(Optional.of(idosoNovo));
        when(repository.save(idosoNovo)).thenReturn(idosoNovo);

        final Idoso result = service.salvarIdoso(idoso);

        assertEquals("Genivaldo", result.getNome());
        assertEquals(1L, result.getId());
    }

}
