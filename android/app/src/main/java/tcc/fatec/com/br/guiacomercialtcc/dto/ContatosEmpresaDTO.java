package tcc.fatec.com.br.guiacomercialtcc.dto;

import android.graphics.drawable.Drawable;

import tcc.fatec.com.br.guiacomercialtcc.commons.TipoContatoEnum;
import tcc.fatec.com.br.guiacomercialtcc.model.TelefoneDTO;

public class ContatosEmpresaDTO {

    private Drawable drawable;
    private TelefoneDTO telefone;
    private String item;
    private int color;
    private TipoContatoEnum tipoContato;
    private String enderecoLocalizacao;

    public ContatosEmpresaDTO(Drawable drawable, TelefoneDTO telefone, int color, TipoContatoEnum tipoContato) {
        this.color = color;
        this.telefone = telefone;
        this.drawable = drawable;
        this.tipoContato = tipoContato;
    }

    public ContatosEmpresaDTO(Drawable drawable, int color, TipoContatoEnum tipoContato, String item) {
        this.color = color;
        this.drawable = drawable;
        this.tipoContato = tipoContato;
        this.item = item;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public TelefoneDTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDTO telefone) {
        this.telefone = telefone;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public TipoContatoEnum getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContatoEnum tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getEnderecoLocalizacao() {
        return enderecoLocalizacao;
    }

    public void setEnderecoLocalizacao(String enderecoLocalizacao) {
        this.enderecoLocalizacao = enderecoLocalizacao;
    }
}
