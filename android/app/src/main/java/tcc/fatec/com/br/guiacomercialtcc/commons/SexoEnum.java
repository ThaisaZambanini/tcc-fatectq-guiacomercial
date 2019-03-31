package tcc.fatec.com.br.guiacomercialtcc.commons;

public enum SexoEnum {

    SELECIONE(0, "E", "Escolha"), FEMININO(1, "F", "Feminino"), MASCULINO(2, "M", "Masculino");

    private int posicao;
    private String codigo;
    private String descricao;

    SexoEnum(int posicao, String codigo, String descricao) {
        this.codigo = codigo;
        this.posicao = posicao;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public int getPosicao() {
        return posicao;
    }

    public static int getPosicaoPorDescricao(String descricao) {
        int posicao = 0;

        for (SexoEnum s : values()) {
            if (s.descricao.equals(descricao)) {
                posicao = s.getPosicao();
            }
        }
        if (posicao == 0) {
            for (SexoEnum s : values()) {
                if (s.codigo.equals(descricao)) {
                    posicao = s.getPosicao();
                }
            }
        }
        return posicao;
    }

    public static SexoEnum getPosicaoPorCodigoOuDescricao(String label) {
        SexoEnum sexo = null;

        for (SexoEnum s : values()) {
            if (s.codigo.equals(label)) {
                sexo = s;
            }
        }
        if (sexo == null) {
            for (SexoEnum s : values()) {
                if (s.descricao.equals(label)) {
                    sexo = s;
                }
            }
        }
        return sexo;
    }
}
