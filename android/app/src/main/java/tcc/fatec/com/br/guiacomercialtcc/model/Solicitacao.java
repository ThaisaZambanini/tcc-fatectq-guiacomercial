package tcc.fatec.com.br.guiacomercialtcc.model;

import com.google.gson.annotations.SerializedName;

public class Solicitacao {

    @SerializedName("tipo")
    private int tipo;

    @SerializedName("nome")
    private String nome;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("email")
    private String email;

    @SerializedName("descricao")
    private String descricao = "";

    @SerializedName("idEmpresa")
    private Long idEmpresa;

    @SerializedName("origem")
    private String origem = "APP_ANDROID";

    @SerializedName("situacao")
    private int situacao;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
