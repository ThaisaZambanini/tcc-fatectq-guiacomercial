package tcc.fatec.com.br.guiacomercialtcc.util;

import tcc.fatec.com.br.guiacomercialtcc.BuildConfig;

public interface Constantes {
    String sessao = "sessao";
    String sessaoUsuario = "sessao-usuario";
    String sessaoLocalizacao = "sessao-localizacao";
    String sessaoBusca = "sessao-busca";
    String sessaoBuscaClube = "sessao-busca-clube";
    String sessaoUsuarioClube = "sessao-usuario-clube";
    String sessaoImagem = "sessao-imagem";
    String sessaoToken = "sessao-token";

    String CONFIG_PERMITE_GPS = "switch_gps";

    String HOST = BuildConfig.URL_API;

    int limitePagina = 10;
    int minimoCaracteresBusca = 3;

}
