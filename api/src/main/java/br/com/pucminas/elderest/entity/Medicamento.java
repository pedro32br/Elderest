package br.com.pucminas.elderest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("medicamentos")
public class Medicamento {

    @Id
    private String id;

    private final String fornecedora;
    private final Integer quantidade;
    private final String substancia;
    private final String situacao;

    public Medicamento(final String id, final String fornecedora,
            final int quantidade,
            final String substancia, final String situacao) {
        super();
        this.id = id;
        this.fornecedora = fornecedora;
        this.quantidade = quantidade;
        this.substancia = substancia;
        this.situacao = situacao;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getFornecedora() {
        return fornecedora;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getSubstancia() {
        return substancia;
    }

    public String getSituacao() {
        return situacao;
    }
}
