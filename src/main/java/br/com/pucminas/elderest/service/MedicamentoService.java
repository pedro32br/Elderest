package br.com.pucminas.elderest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import br.com.pucminas.elderest.model.Medicamento;
import br.com.pucminas.elderest.model.SituacaoEnum;
import br.com.pucminas.elderest.repository.MedicamentoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public Medicamento salvarMedicamento(final Medicamento medicamento) {
        medicamento.setNome(StringUtils.capitalize(medicamento.getNome().toLowerCase()));
        final Optional<Medicamento> optional = medicamentoRepository.findByNome(medicamento.getNome());
        optional.ifPresent(value -> medicamento.setId(value.getId()));
        medicamento.setSituacao(updateSituacao(medicamento.getQuantidade()));
        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> getMedicamento(final String nome) {
        final Optional<Medicamento> optional = medicamentoRepository.findByNome(StringUtils.capitalize(nome.toLowerCase()));
        if(optional.isPresent()) {
            return optional.stream().toList();
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Transactional
    public void deleteMedicamento(final String nome) {
        medicamentoRepository.deleteByNome(StringUtils.capitalize(nome.toLowerCase()));
    }

    protected SituacaoEnum updateSituacao(final Integer quantidade) {
        SituacaoEnum situacao = SituacaoEnum.EM_ESTOQUE;
        if(quantidade == 0) {
            situacao = SituacaoEnum.EM_FALTA;
        }
        else if(quantidade < 5) {
            situacao = SituacaoEnum.ENCOMENDAR;
        }
        return situacao;
    }
}
