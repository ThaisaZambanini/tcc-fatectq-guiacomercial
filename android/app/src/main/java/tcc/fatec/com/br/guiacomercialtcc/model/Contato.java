package tcc.fatec.com.br.guiacomercialtcc.model;

import com.google.gson.annotations.SerializedName;

public class Contato {

    @SerializedName("nome")
    private String nome;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("celular")
    private String celular;

    @SerializedName("email")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
