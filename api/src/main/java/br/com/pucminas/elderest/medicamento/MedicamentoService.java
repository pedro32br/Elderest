package br.com.pucminas.elderest.medicamento;

import java.util.List;

public interface MedicamentoService {

    MedicamentoDTO save(final MedicamentoDTO medicamentoDTO);

    List<Medicamento> getMedicamento(final String id);

    List<Medicamento> getAllMedicamentos();

    void deleteMedicamento(final String id);

}
