package tcc.fatec.com.br.guiacomercialtcc.rb;

import com.google.gson.annotations.SerializedName;

import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;

public class CadastroUsuarioRB {

    @SerializedName("cadastro")
    private Usuario cadastro;

    public Usuario getCadastro() {
        return cadastro;
    }

    public void setCadastro(Usuario cadastro) {
        this.cadastro = cadastro;
    }
}
