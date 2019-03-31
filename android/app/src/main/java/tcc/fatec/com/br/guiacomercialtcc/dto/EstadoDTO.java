package tcc.fatec.com.br.guiacomercialtcc.dto;

import com.google.gson.annotations.SerializedName;

public class EstadoDTO {

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    public EstadoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

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

    @Override
    public String toString() {
        return getNome();
    }
}
