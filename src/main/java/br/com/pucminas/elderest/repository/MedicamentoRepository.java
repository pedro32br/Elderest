package br.com.pucminas.elderest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pucminas.elderest.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT s FROM Medicamento s WHERE s.nome = ?1")
    Optional<Medicamento> findByNome(String nome);

    void deleteByNome(String nome);

}
