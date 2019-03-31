package tcc.fatec.com.br.guiacomercialtcc.rb;

import com.google.gson.annotations.SerializedName;

import tcc.fatec.com.br.guiacomercialtcc.model.Solicitacao;

public class SolicitacaoRB {

    @SerializedName("solicitacao")
    private Solicitacao solicitacao;

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
