package br.com.pucminas.elderest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pucminas.elderest.model.Medicamento;
import br.com.pucminas.elderest.model.SituacaoEnum;
import br.com.pucminas.elderest.repository.MedicamentoRepository;

@ExtendWith(MockitoExtension.class)
class MedicamentoServiceTest {

    @InjectMocks
    private MedicamentoService service;

    @Mock
    private MedicamentoRepository repository;

    Medicamento medicamento;

    @BeforeEach
    void beforeEach() {
        medicamento = new Medicamento("Adalat", 4, "Rivotril");
    }

    @Test
    void Should_Save_Success_When_Send_Ok_Model() {
        when(repository.findByNome(medicamento.getNome())).thenReturn(Optional.empty());
        when(repository.save(medicamento)).thenReturn(medicamento);

        final Medicamento result = service.salvarMedicamento(medicamento);

        assertEquals("Adalat", result.getNome());
        assertEquals(SituacaoEnum.ENCOMENDAR, result.getSituacao());
    }

    @Test
    void Should_Update_Success_When_Send_Same_Model() {
        final Medicamento medicamentoAchado = new Medicamento("Adalat", 6, "Rivotril");
        medicamento.setQuantidade(6);

        when(repository.findByNome(medicamento.getNome())).thenReturn(Optional.of(medicamentoAchado));
        when(repository.save(any())).thenReturn(medicamento);

        final Medicamento result = service.salvarMedicamento(medicamento);

        assertEquals("Adalat", result.getNome());
        assertEquals(SituacaoEnum.EM_ESTOQUE, result.getSituacao());
    }

}
