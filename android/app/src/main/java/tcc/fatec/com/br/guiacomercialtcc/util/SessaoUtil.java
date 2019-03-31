package tcc.fatec.com.br.guiacomercialtcc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.GeoLocalizacaoDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ImagensDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ParametroBuscaDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;

public class SessaoUtil {

    public static boolean setToken(Context context, String token) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoToken, token);
        return pref.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        String token = pref.getString(Constantes.sessaoToken, "");
        return token;
    }

    public static boolean setCidade(Context context, CidadeDTO cidade) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoUsuario, new Gson().toJson(cidade));
        return pref.commit();
    }

    public static CidadeDTO getCidade(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        CidadeDTO acesso = new Gson().fromJson(pref.getString(Constantes.sessaoUsuario, ""), CidadeDTO.class);
        return acesso;
    }

    public static boolean setGeoLocalizacao(Context context, GeoLocalizacaoDTO localizacao) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoLocalizacao, new Gson().toJson(localizacao));
        return pref.commit();
    }

    public static GeoLocalizacaoDTO getGeoLocalizacao(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        GeoLocalizacaoDTO localizacao = new Gson().fromJson(pref.getString(Constantes.sessaoLocalizacao, ""), GeoLocalizacaoDTO.class);
        return localizacao;
    }

    public static boolean setParametrosBusca(Context context, ParametroBuscaDTO parametro) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoBusca, new Gson().toJson(parametro));
        return pref.commit();
    }

    public static ParametroBuscaDTO getParametrosBusca(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        ParametroBuscaDTO dto = new Gson().fromJson(pref.getString(Constantes.sessaoBusca, ""), ParametroBuscaDTO.class);
        if (dto == null) {
            dto = new ParametroBuscaDTO();
            SessaoUtil.setParametrosBusca(context, dto);
        }
        return dto;
    }

    public static boolean setParametrosBuscaClube(Context context, ParametroBuscaDTO parametro) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoBuscaClube, new Gson().toJson(parametro));
        return pref.commit();
    }

    public static ParametroBuscaDTO getParametrosBuscaClube(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        ParametroBuscaDTO dto = new Gson().fromJson(pref.getString(Constantes.sessaoBuscaClube, ""), ParametroBuscaDTO.class);
        if (dto == null) {
            dto = new ParametroBuscaDTO();
            SessaoUtil.setParametrosBuscaClube(context, dto);
        }
        return dto;
    }

    public static boolean setUsuario(Context context, Usuario usuario) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoUsuarioClube, new Gson().toJson(usuario));
        return pref.commit();
    }

    public static Usuario getUsuario(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        Usuario usuario = new Gson().fromJson(pref.getString(Constantes.sessaoUsuarioClube, ""), Usuario.class);
        return usuario;
    }


    public static boolean setImagens(Context context, ImagensDTO imagem) {
        SharedPreferences.Editor pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE).edit();
        pref.putString(Constantes.sessaoImagem, new Gson().toJson(imagem));
        return pref.commit();
    }

    public static ImagensDTO getImagens(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constantes.sessao, Context.MODE_PRIVATE);
        ImagensDTO imagens = new Gson().fromJson(pref.getString(Constantes.sessaoImagem, ""), ImagensDTO.class);
        return imagens;
    }

    public static void limpar(Context context) {
        setCidade(context, null);
        setParametrosBusca(context, null);
        setUsuario(context, null);
        setGeoLocalizacao(context, null);
    }

}
