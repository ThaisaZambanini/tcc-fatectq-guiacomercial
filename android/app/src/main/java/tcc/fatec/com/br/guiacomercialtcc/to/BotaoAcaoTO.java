package tcc.fatec.com.br.guiacomercialtcc.to;

public class BotaoAcaoTO {

    private Integer icone;
    private String nome;

    public BotaoAcaoTO(String nome) {
        this.nome = nome;
    }
    public BotaoAcaoTO(Integer icone, String nome) {
        this.icone = icone;
        this.nome = nome;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void onClick() {
    }

}
