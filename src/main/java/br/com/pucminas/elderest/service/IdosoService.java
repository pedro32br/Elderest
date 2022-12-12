package br.com.pucminas.elderest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pucminas.elderest.exception.exceptions.BadRequestException;
import br.com.pucminas.elderest.model.Idoso;
import br.com.pucminas.elderest.repository.IdosoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class IdosoService {

    private final IdosoRepository idosoRepository;

    public Idoso salvarIdoso(final Idoso idoso) {
        idoso.setCpf(formataCpf(idoso.getCpf()));
        final Optional<Idoso> optional = idosoRepository.findByCpf(idoso.getCpf());
        optional.ifPresent(value -> idoso.setId(value.getId()));
        return idosoRepository.save(idoso);
    }

    public Idoso getIdoso(final String cpf) {
        final Optional<Idoso> cpfOptional = idosoRepository.findByCpf(formataCpf(cpf));
        return cpfOptional.orElse(null);
    }

    public List<Idoso> getAllIdosos() {
        return idosoRepository.findAll();
    }

    protected String formataCpf(final String cpf) {
        final String cpfFormatado = cpf.replaceAll("\\D", "");
        if(cpfFormatado.length() == 11) {
            return cpfFormatado;
        }
        throw new BadRequestException("CPF incorreto");
    }
}
