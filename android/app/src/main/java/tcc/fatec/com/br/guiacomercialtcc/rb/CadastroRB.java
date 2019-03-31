package tcc.fatec.com.br.guiacomercialtcc.rb;

import com.google.gson.annotations.SerializedName;

import tcc.fatec.com.br.guiacomercialtcc.model.Contato;
import tcc.fatec.com.br.guiacomercialtcc.model.Endereco;

public class CadastroRB {

    @SerializedName("nome")
    private String nome;

    @SerializedName("documento")
    private String documento;

    @SerializedName("ramo")
    private String ramo;

    @SerializedName("origem")
    private String origem;

    @SerializedName("endereco")
    private Endereco endereco;

    @SerializedName("contato")
    private Contato contato;

    public CadastroRB() {
        setOrigem("APP_ANDROID");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
