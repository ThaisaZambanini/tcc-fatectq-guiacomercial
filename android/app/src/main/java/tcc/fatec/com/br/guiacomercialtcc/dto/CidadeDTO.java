package tcc.fatec.com.br.guiacomercialtcc.dto;

public class CidadeDTO {

    private Long id;
    private String nome;
    private EstadoDTO estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoDTO getEstadoDTO() {
        return estado;
    }

    public void setEstadoDTO(EstadoDTO estado) {
        this.estado = estado;
    }
}
