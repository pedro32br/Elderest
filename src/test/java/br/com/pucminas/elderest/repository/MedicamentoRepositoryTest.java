package br.com.pucminas.elderest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import br.com.pucminas.elderest.model.Medicamento;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MedicamentoRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MedicamentoRepository repository;

    @BeforeAll
    void before() {
        final List<Medicamento> list = new ArrayList<>();
        list.add(new Medicamento("nome", 2, "substancia"));
        list.add(new Medicamento("adatalt", 3, "substancia"));
        list.add(new Medicamento("cobalto", 0, "substancia"));
        repository.saveAll(list);
    }

    @Test
    void Should_Update_Column_When_Save_With_Same_Id() {
        repository.save(new Medicamento("cuzcuz", 2, "Substancia2"));
        final Optional<Medicamento> result = repository.findById(4L);
        assertTrue(result.isPresent());
        assertEquals("cuzcuz", result.get().getNome());
        assertEquals("Substancia2", result.get().getSubstancia());
        assertEquals(2, result.get().getQuantidade());
    }

    @Test
    void Should_Return_3_When_Save_All_3_Medicamentos() {
        assertEquals(3, repository.findAll().size());
    }

}
