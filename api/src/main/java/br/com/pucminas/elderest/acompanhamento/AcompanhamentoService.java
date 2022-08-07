package br.com.pucminas.elderest.acompanhamento;

import java.util.List;

public interface AcompanhamentoService {

    AcompanhamentoDTO save(final AcompanhamentoDTO medicamentoDTO);

    List<Acompanhamento> getAcompanhamento(final String id);

    void deleteAcompanhamento(final String id);
}
