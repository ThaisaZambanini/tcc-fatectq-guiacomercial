package tcc.fatec.com.br.guiacomercialtcc.dto;

public class CidadeDTO {

    private Long id;
    private String uuid;
    private String nome;
    private String estado;
    private boolean permiteCadastroUsuario;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isPermiteCadastroUsuario() {
        return permiteCadastroUsuario;
    }

    public void setPermiteCadastroUsuario(boolean permiteCadastroUsuario) {
        this.permiteCadastroUsuario = permiteCadastroUsuario;
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
}
