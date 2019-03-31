package tcc.fatec.com.br.guiacomercialtcc.model;

import com.google.gson.annotations.SerializedName;

import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;

public class Usuario {

    @SerializedName("idUsuario")
    private Long idUsuario;

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cpf")
    private String cpf;

    @SerializedName("dataNascimento")
    private String dataNascimento;

    @SerializedName("sexo")
    private String sexo;

    @SerializedName("celular")
    private String celular;

    @SerializedName("email")
    private String email;

    @SerializedName("permiteEnvioEmail")
    private boolean permiteEnvioEmail;

    @SerializedName("permiteEnvioSms")
    private boolean permiteEnvioSms;

    @SerializedName("foto")
    private String foto;

    @SerializedName("mudouFoto")
    private boolean mudouFoto;

    @SerializedName("dataValidade")
    private String dataValidade;

    @SerializedName("envioEmail")
    private boolean envioEmail;

    @SerializedName("envioSms")
    private boolean envioSms;

    @SerializedName("cidade")
    private CidadeDTO cidade;

    @SerializedName("cartao")
    private CartaoClube cartao;

    private boolean lembrarLogin;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public boolean isPermiteEnvioEmail() {
        return permiteEnvioEmail;
    }

    public void setPermiteEnvioEmail(boolean permiteEnvioEmail) {
        this.permiteEnvioEmail = permiteEnvioEmail;
    }

    public boolean isPermiteEnvioSms() {
        return permiteEnvioSms;
    }

    public void setPermiteEnvioSms(boolean permiteEnvioSms) {
        this.permiteEnvioSms = permiteEnvioSms;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isMudouFoto() {
        return mudouFoto;
    }

    public void setMudouFoto(boolean mudouFoto) {
        this.mudouFoto = mudouFoto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    public boolean isEnvioSms() {
        return envioSms;
    }

    public void setEnvioSms(boolean envioSms) {
        this.envioSms = envioSms;
    }

    public boolean isEnvioEmail() {
        return envioEmail;
    }

    public void setEnvioEmail(boolean envioEmail) {
        this.envioEmail = envioEmail;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public CartaoClube getCartao() {
        return cartao;
    }

    public void setCartao(CartaoClube cartao) {
        this.cartao = cartao;
    }

    public boolean isLembrarLogin() {
        return lembrarLogin;
    }

    public void setLembrarLogin(boolean lembrarLogin) {
        this.lembrarLogin = lembrarLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
