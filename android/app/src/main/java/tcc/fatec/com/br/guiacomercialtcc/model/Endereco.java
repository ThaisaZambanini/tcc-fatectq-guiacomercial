package tcc.fatec.com.br.guiacomercialtcc.model;

import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;

public class Endereco {

    private CidadeDTO cidade;

    private String bairro;
    private String numero;
    private String cep;
    private String endereco;

    private String linha1;
    private String linha2;
    private String linha3;

    private String logradouro;
    private Long idCidade;

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLinha1() {
        return linha1;
    }

    public void setLinha1(String linha1) {
        this.linha1 = linha1;
    }

    public String getLinha2() {
        return linha2;
    }

    public void setLinha2(String linha2) {
        this.linha2 = linha2;
    }

    public String getLinha3() {
        return linha3;
    }

    public void setLinha3(String linha3) {
        this.linha3 = linha3;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }
}
