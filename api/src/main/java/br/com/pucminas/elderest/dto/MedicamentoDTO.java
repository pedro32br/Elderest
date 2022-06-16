package br.com.pucminas.elderest.dto;

public class MedicamentoDTO {

    private final String id;

    private final String fornecedora;
    private final Integer quantidade;
    private final String substancia;

    public MedicamentoDTO(final String id, final String fornecedora,
            final int quantidade,
            final String substancia) {
        super();
        this.id = id;
        this.fornecedora = fornecedora;
        this.quantidade = quantidade;
        this.substancia = substancia;
    }

    public String getId() {
        return id;
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
}
