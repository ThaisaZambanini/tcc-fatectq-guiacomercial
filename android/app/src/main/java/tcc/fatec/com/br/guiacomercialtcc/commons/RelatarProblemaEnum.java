package tcc.fatec.com.br.guiacomercialtcc.commons;

public enum RelatarProblemaEnum {
    SELECIONE(0, "Selecione"), TELEFONE(1, "TelefoneDTO incorreto"), ENDERECO(2, "Endereço incorreto"), EMPRESA(3, "A empresa não existe"), OUTROS(4, "Outros");

    private int codigo;
    private String descricao;

    RelatarProblemaEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

}
