package tcc.fatec.com.br.guiacomercialtcc.commons;

public enum TipoSolicitacaoEnum {

    SELECIONE(0, "Selecione"), USUARIO(1, "Quero apenas relatar um problema"), PROPRIETARIO(2, "Sou responsável pela empresa");

    private int codigo;
    private String descricao;

    TipoSolicitacaoEnum(int codigo, String descricao) {
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
