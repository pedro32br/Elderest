package br.com.pucminas.elderest.medicamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MedicamentoRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MedicamentoRepository repository;

    @BeforeEach
    void before() {
        final List<Medicamento> list = new ArrayList<>();
        list.add(new Medicamento(1L, "nome", 2, "substancia", "situacao"));
        list.add(new Medicamento(2L, "adatalt", 3, "substancia", "situacao"));
        list.add(new Medicamento(3L, "cobalto", 0, "substancia", "situacao"));
        repository.saveAll(list);
    }

    @Test
    void Should_Return_3_When_Save_All_3_Medicamentos() {
        assertEquals(3, repository.findAll().size());
    }

    @Test
    void Should_Update_Column_When_Save_With_Same_Id() {
        repository.save(new Medicamento(3L, "cuzcuz", 2, "Substancia2", "situacao2"));
        final Optional<Medicamento> result = repository.findById(3L);
        assertTrue(result.isPresent());
        assertEquals("cuzcuz", result.get().getNome());
        assertEquals("Substancia2", result.get().getSubstancia());
        assertEquals("situacao2", result.get().getSituacao());
        assertEquals(2, result.get().getQuantidade());
    }
}
