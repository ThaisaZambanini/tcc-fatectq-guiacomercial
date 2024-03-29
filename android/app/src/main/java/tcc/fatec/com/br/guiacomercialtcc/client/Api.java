package tcc.fatec.com.br.guiacomercialtcc.client;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import tcc.fatec.com.br.guiacomercialtcc.dto.CategoriaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ClientEmpresaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.EstadoDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.MensagemDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Contato;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;

public interface Api {

    @GET("estados")
    Call<List<EstadoDTO>> findEstados();

    @GET("cidades/estado/{id}")
    Call<List<CidadeDTO>> findCidades(@Path("id") Long id);

    @GET("categorias/cidade/{id}")
    Call<List<CategoriaDTO>> findCategorias(@Path("id") Long id);

    @GET("empresas/busca")
    Call<ClientEmpresaDTO> findCategoriaEmpresaTermo(@Header("X-Cidade-Id") Long idCidade, @QueryMap Map<String, Object> options);

    @GET("empresas/{id}")
    Call<Empresa> findEmpresaDetalhes(@Path("id") Long id);

    @POST("mensagens")
    Call<MensagemDTO> sendSolicitacaoContato(@Body Contato contato);

}
