package br.com.pucminas.elderest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pucminas.elderest.model.Acompanhamento;
import br.com.pucminas.elderest.repository.AcompanhamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcompanhamentoService {

    private final AcompanhamentoRepository acompanhamentoRepository;

    public Acompanhamento salvarAcompanhamento(final Acompanhamento acompanhamento) {
        return acompanhamentoRepository.save(acompanhamento);
    }

    public Acompanhamento getAcompanhamento(final Long id) {
        final Optional<Acompanhamento> acompanhamento = acompanhamentoRepository.findById(id);
        return acompanhamento.orElse(null);
    }
}
