package tcc.fatec.com.br.guiacomercialtcc.model;

import com.google.gson.annotations.SerializedName;

public class CartaoClube {

    @SerializedName("id")
    private String id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("logo")
    private String logo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
