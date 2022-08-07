package br.com.pucminas.elderest.medicamento;

public class MedicamentoDTO {

    private final Long id;

    private final String nome;
    private final Integer quantidade;
    private final String substancia;

    public MedicamentoDTO(final Long id, final String nome,
            final int quantidade,
            final String substancia) {
        super();
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.substancia = substancia;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getSubstancia() {
        return substancia;
    }
}
