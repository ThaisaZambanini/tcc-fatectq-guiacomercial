package tcc.fatec.com.br.guiacomercialtcc.dto;

public class StatusEmpresaDTO {

    private boolean aberto;
    private String texto;
    private String tipo;

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
