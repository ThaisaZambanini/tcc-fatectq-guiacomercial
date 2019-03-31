package tcc.fatec.com.br.guiacomercialtcc.model;

import java.io.Serializable;
import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.dto.StatusEmpresaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.VantagemDTO;

public class Empresa implements Serializable {

    private Long id;
    private String nome;
    private String categoria;
    private String linkFoneja;
    private String linkSite;
    private String linkFacebook;
    private String linkInstagram;
    private String linkTwitter;
    private String email;
    private String logo;

    private Endereco endereco;
    private String distancia;
    private boolean clubeVantagens;

    private StatusEmpresaDTO status;
    private List<TelefoneDTO> telefones;
    private List<Horario> horarios;
    private List<Cartao> cartoes;

    private List<Imagem> fotos;

    private List<VantagemDTO> vantagens;

    private String maisInformacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLinkFoneja() {
        return linkFoneja;
    }

    public void setLinkFoneja(String linkFoneja) {
        this.linkFoneja = linkFoneja;
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

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public boolean isClubeVantagens() {
        return clubeVantagens;
    }

    public void setClubeVantagens(boolean clubeVantagens) {
        this.clubeVantagens = clubeVantagens;
    }

    public StatusEmpresaDTO getStatus() {
        return status;
    }

    public void setStatus(StatusEmpresaDTO status) {
        this.status = status;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Imagem> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagem> fotos) {
        this.fotos = fotos;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public List<VantagemDTO> getVantagens() {
        return vantagens;
    }

    public void setVantagens(List<VantagemDTO> vantagens) {
        this.vantagens = vantagens;
    }

    public String getLinkTwitter() {
        return linkTwitter;
    }

    public void setLinkTwitter(String linkTwitter) {
        this.linkTwitter = linkTwitter;
    }

    public String getMaisInformacoes() {
        return maisInformacoes;
    }

    public void setMaisInformacoes(String maisInformacoes) {
        this.maisInformacoes = maisInformacoes;
    }
}
