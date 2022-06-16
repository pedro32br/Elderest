package br.com.pucminas.elderest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.pucminas.elderest.dto.MedicamentoDTO;
import br.com.pucminas.elderest.entity.Medicamento;
import br.com.pucminas.elderest.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    private final Mapper mapper;
    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(final Mapper mapper, final MedicamentoRepository medicamentoRepository) {
        this.mapper = mapper;
        this.medicamentoRepository = medicamentoRepository;
    }

    public MedicamentoDTO save(final MedicamentoDTO medicamentoDTO) {
        final Medicamento medicamento = medicamentoRepository.save(mapper.dtoToMedicamento(medicamentoDTO));
        return mapper.medicamentoToDto(medicamento);
    }

    public List<Medicamento> getMedicamento(final String id) {
        if(StringUtils.hasText(id)) {
            final Optional<Medicamento> optional = medicamentoRepository.findById(id);
            if(optional.isPresent()) {
                return optional.stream().collect(Collectors.toList());
            }
            else {
                return new ArrayList<>();
            }
        }
        else {
            return medicamentoRepository.findAll();
        }
    }
}
