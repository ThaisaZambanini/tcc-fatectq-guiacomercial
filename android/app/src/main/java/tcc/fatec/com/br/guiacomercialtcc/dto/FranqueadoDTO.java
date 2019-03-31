package tcc.fatec.com.br.guiacomercialtcc.dto;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.model.TelefoneDTO;

public class FranqueadoDTO {

    private String nome;
    private String endereco1;
    private String endereco2;
    private String logo;
    private String linkSite;
    private String linkFacebook;
    private String linkInstagram;
    private String linkTwitter;
    private String email;
    private List<TelefoneDTO> telefones;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco1() {
        return endereco1;
    }

    public void setEndereco1(String endereco1) {
        this.endereco1 = endereco1;
    }

    public String getEndereco2() {
        return endereco2;
    }

    public void setEndereco2(String endereco2) {
        this.endereco2 = endereco2;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLinkSite() {
        return linkSite;
    }

    public void setLinkSite(String linkSite) {
        this.linkSite = linkSite;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getLinkTwitter() {
        return linkTwitter;
    }

    public void setLinkTwitter(String linkTwitter) {
        this.linkTwitter = linkTwitter;
    }
}
