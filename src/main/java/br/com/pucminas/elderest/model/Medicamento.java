package br.com.pucminas.elderest.model;

import static br.com.pucminas.elderest.utils.ConstantsUtils.NOT_BLANK;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "MEDICAMENTO")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NOT_BLANK)
    @Size(max = 50, message = "Nome precisa ter valor maximo de 50 characteres")
    private String nome;

    @PositiveOrZero(message = "Quantidade nao pode ser negativo e deve conter apenas numeros")
    private Integer quantidade;

    @NotBlank(message = NOT_BLANK)
    @Size(max = 50, message = "Substancia precisa ter valor maximo de 50 characteres")
    private String substancia;

    @Enumerated(EnumType.STRING)
    private SituacaoEnum situacao;

    public Medicamento(final String nome,
            final int quantidade,
            final String substancia) {
        super();
        this.nome = nome;
        this.quantidade = quantidade;
        this.substancia = substancia;
    }

    protected Medicamento() {

    }
}
