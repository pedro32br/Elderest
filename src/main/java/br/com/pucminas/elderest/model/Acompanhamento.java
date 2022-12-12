package br.com.pucminas.elderest.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Acompanhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String funcionario;

    @Lob
    private String descricao;

    @Digits(integer = 2, fraction = 0)
    private Integer pressaoArterialMax;

    @Digits(integer = 2, fraction = 0)
    private Integer pressaoArterialMin;

    @Digits(integer = 3, fraction = 0)
    private Integer frequenciaRespiratoria;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 1)
    private Double temperatura;

    @Digits(integer = 3, fraction = 0)
    private Integer frequenciaCardiaca;

    @Digits(integer = 3, fraction = 0)
    private Integer saturacao;

    @Digits(integer = 3, fraction = 0)
    private Integer dextro;

    @Digits(integer = 4, fraction = 2)
    private Double diurese;

    @PastOrPresent
    private LocalDateTime dataAcompanhamento;

    @NotNull
    @JoinColumn(name = "idoso_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Idoso idoso;

    public Acompanhamento(final String funcionario, final String descricao,
            final Integer pressaoArterialMax, final Integer pressaoArterialMin,
            final Integer frequenciaRespiratoria,
            final Double temperatura, final Integer frequenciaCardiaca,
            final Integer saturacao, final Integer dextro, final Double diurese,
            final Idoso idoso) {
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.pressaoArterialMax = pressaoArterialMax;
        this.pressaoArterialMin = pressaoArterialMin;
        this.frequenciaRespiratoria = frequenciaRespiratoria;
        this.temperatura = temperatura;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.saturacao = saturacao;
        this.dextro = dextro;
        this.diurese = diurese;
        this.dataAcompanhamento = LocalDateTime.now();
        this.idoso = idoso;
    }
}
