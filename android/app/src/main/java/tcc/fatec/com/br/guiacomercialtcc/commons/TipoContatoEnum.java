package tcc.fatec.com.br.guiacomercialtcc.commons;

public enum TipoContatoEnum {

    SITE("Site"), FACEBOOK("Facebook"), EMAIL("E-mail"), COMPARTILHAR("Compartilhar"), LOCALIZACAO("Como chegar?"), FONE("Fone"), INSTAGRAM("Instagram"), WHATSAPP("WhatsApp"), CELULAR("Celular");

    private String descricao;

    TipoContatoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
