package br.com.pucminas.elderest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pucminas.elderest.model.Idoso;

@Repository
public interface IdosoRepository extends JpaRepository<Idoso, Long> {

    @Query("SELECT i FROM Idoso i WHERE i.cpf = ?1")
    Optional<Idoso> findByCpf(String cpf);
}
