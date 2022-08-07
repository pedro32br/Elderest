package br.com.pucminas.elderest.medicamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pucminas.elderest.utils.Mapper;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final Mapper mapper;
    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(final Mapper mapper, final MedicamentoRepository medicamentoRepository) {
        this.mapper = mapper;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public MedicamentoDTO save(final MedicamentoDTO medicamentoDTO) {
        final Medicamento medicamento = medicamentoRepository.save(mapper.dtoToMedicamento(medicamentoDTO));
        return mapper.medicamentoToDto(medicamento);
    }

    @Override
    public List<Medicamento> getMedicamento(final String nome) {
        final Optional<Medicamento> optional = medicamentoRepository.findByNome(nome);
        if(optional.isPresent()) {
            return optional.stream().collect(Collectors.toList());
        }
        else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public void deleteMedicamento(final String nome) {
        medicamentoRepository.deleteByNome(nome);
    }
}
