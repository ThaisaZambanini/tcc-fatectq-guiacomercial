package tcc.fatec.com.br.guiacomercialtcc.commons;

public enum LinkAppsEnum {

    SITE("https://www.foneja.com"), ANDROID("https://play.google.com/store/apps/details?id=br.xbrunots.foneja"), IOS("https://itunes.apple.com/br/app/foneja/id1016726311?l=pt");

    LinkAppsEnum(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
