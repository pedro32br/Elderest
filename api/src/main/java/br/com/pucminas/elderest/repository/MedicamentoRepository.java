package br.com.pucminas.elderest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.pucminas.elderest.entity.Medicamento;

public interface MedicamentoRepository extends MongoRepository<Medicamento, String> {
}
