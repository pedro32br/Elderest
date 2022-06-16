package br.com.pucminas.elderest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import br.com.pucminas.elderest.entity.Medicamento;
import br.com.pucminas.elderest.repository.MedicamentoRepository;

@SpringBootApplication
@EnableMongoRepositories
public class ElderestApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ElderestApplication.class);

    @Autowired
    MedicamentoRepository medicamentoRepo;

    public static void main(final String[] args) {
        SpringApplication.run(ElderestApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        log.info("Criando medicamentos dento do MongoDB...");
        medicamentoRepo.save(new Medicamento("Adalat", "Bayer", 5, "Nifedipino 10mg", "OK"));
        medicamentoRepo.save(new Medicamento("Xarelto", "Bayer", 0, "Rivaroxabana 20mg", "EM FALTA"));
        medicamentoRepo.save(new Medicamento("Torsilax", "Neo Quimica", 2, "Paracetamol 1g", "ENCOMENDAR"));
        medicamentoRepo.save(new Medicamento("Dorflex", "Sanofi Aventis", 1, "Dipirona monoidratada 300mg", "ENCOMENDAR"));
        medicamentoRepo.save(new Medicamento("Tylenol", "Mediquimica", 6, "Paracetamol 750mg", "OK"));
        medicamentoRepo.findAll().forEach(m -> log.info("Id: " + m.getId() + "Qt: " + m.getQuantidade()));
        log.info("Medicamentos criados com sucesso");
    }
}
