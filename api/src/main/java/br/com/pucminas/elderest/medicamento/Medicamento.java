package br.com.pucminas.elderest.medicamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MEDICAMENTO")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOME", length = 50, nullable = false, unique = true)
    private String nome;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "SUBSTANCIA", length = 50, nullable = false)
    private String substancia;

    @Transient
    private String situacao;

    public Medicamento(final Long id, final String nome,
            final int quantidade,
            final String substancia, final String situacao) {
        super();
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.substancia = substancia;
        this.situacao = situacao;
    }

    protected Medicamento() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getSubstancia() {
        return substancia;
    }

    public void setSubstancia(final String substancia) {
        this.substancia = substancia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }
}
