package tcc.fatec.com.br.guiacomercialtcc.enums;

public enum TipoPessoaEnum {

    JURIDICA("Pessoa Jurídica"), FISICA("Pessoa Física");

    TipoPessoaEnum(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
