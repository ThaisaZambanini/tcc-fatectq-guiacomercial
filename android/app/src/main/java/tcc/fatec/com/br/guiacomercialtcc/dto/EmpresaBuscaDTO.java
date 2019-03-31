package tcc.fatec.com.br.guiacomercialtcc.dto;

public class EmpresaBuscaDTO {

    private Long id;
    private String nome;
    private String categoria;
    private String logo;
    private String distancia;
    private boolean clubeVantagens;
    private EnderecoDTO endereco;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public boolean isClubeVantagens() {
        return clubeVantagens;
    }

    public void setClubeVantagens(boolean clubeVantagens) {
        this.clubeVantagens = clubeVantagens;
    }
}
