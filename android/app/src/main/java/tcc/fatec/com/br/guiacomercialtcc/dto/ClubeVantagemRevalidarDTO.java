package tcc.fatec.com.br.guiacomercialtcc.dto;

public class ClubeVantagemRevalidarDTO {

    private String dataValidade;
    private String aviso;
    private boolean expirou;

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public boolean isExpirou() {
        return expirou;
    }

    public void setExpirou(boolean expirou) {
        this.expirou = expirou;
    }

}
