package tcc.fatec.com.br.guiacomercialtcc.rb;

public class DispositivoRB {

    private String token;
    private String tipo;
    private String origem;

    public DispositivoRB() {
        setTipo("A");
        setOrigem("F");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

}
