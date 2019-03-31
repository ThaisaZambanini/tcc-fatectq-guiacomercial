package tcc.fatec.com.br.guiacomercialtcc.model;

public class TelefoneDTO {

    private String exibicao;
    private String tipo;
    private String ddd;
    private String numero;
    private String discagem;

    public String getExibicao() {
        return exibicao;
    }

    public void setExibicao(String exibicao) {
        this.exibicao = exibicao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDiscagem() {
        return discagem;
    }

    public void setDiscagem(String discagem) {
        this.discagem = discagem;
    }
}
