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

    @GET("estado/")
    Call<List<EstadoDTO>> findEstados();

    @GET("cidade/estado/{id}")
    Call<List<CidadeDTO>> findCidades(@Path("id") Long id);

    @GET("categoria/")
    Call<List<CategoriaDTO>> findCategorias(@Header("X-Cidade-Id") Long idCidade);

    @GET("empresa/busca")
    Call<ClientEmpresaDTO> findCategoriaEmpresaTermo(@Header("X-Cidade-Id") Long idCidade, @QueryMap Map<String, Object> options);

    @GET("empresa/empresa/")
    Call<Empresa> findEmpresaDetalhes(@Header("X-Cidade-Id") Long idCidade, @QueryMap Map<String, Long> options);

    @POST("contato/adicionar")
    Call<MensagemDTO> sendSolicitacaoContato(@Body Contato contato);


}
