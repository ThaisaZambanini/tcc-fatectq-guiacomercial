package tcc.fatec.com.br.guiacomercialtcc.rb;

import com.google.gson.annotations.SerializedName;

import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;

public class AtualizacaoUsuarioRB {

    @SerializedName("atualizacao")
    private Usuario atualizacao;

    public Usuario getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Usuario atualizacao) {
        this.atualizacao = atualizacao;
    }
}
