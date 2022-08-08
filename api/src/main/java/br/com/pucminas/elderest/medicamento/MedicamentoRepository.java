package br.com.pucminas.elderest.medicamento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT s FROM Medicamento s WHERE s.nome = ?1")
    Optional<Medicamento> findByNome(String nome);

    @Query("DELETE FROM Medicamento WHERE nome = ?1")
    void deleteByNome(String nome);

}
