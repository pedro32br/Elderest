package br.com.pucminas.elderest.model;

import static br.com.pucminas.elderest.utils.ConstantsUtils.NOT_BLANK;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@NoArgsConstructor
@DynamicUpdate
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Idoso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = NOT_BLANK)
    @Size(max = 200, min = 2, message = "Nome precisa ter valor maximo de 200 characteres e minimo 2")
    private String nome;

    @CPF
    private String cpf;

    @Max(value = 150, message = "Como voce viveu tudo isso?")
    @Min(value = 60, message = "Idade minima para se internar em nossa clinica é de 60 anos")
    @NotNull
    private Integer idade;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInternacao;

    @PastOrPresent
    private LocalDate dataBaixa;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "idoso")
    @JsonManagedReference
    private List<Acompanhamento> acompanhamentos;

    public Idoso(final String nome, final String cpf,
            final Integer idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
}
