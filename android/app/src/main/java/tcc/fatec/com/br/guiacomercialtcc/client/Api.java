package tcc.fatec.com.br.guiacomercialtcc.client;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import tcc.fatec.com.br.guiacomercialtcc.dto.CategoriaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ClientEmpresaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.EstadoDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;

public interface Api {

    @GET("estado/")
    Call<List<EstadoDTO>> findEstados();

    @GET("cidade/estado")
    Call<List<CidadeDTO>> findCidades(@Query("idEstado") Long idEstado);

    @GET("categoria/")
    Call<List<CategoriaDTO>> findCategorias(@Header("X-Cidade-Id") Long idCidade);

    @GET("empresa/busca")
    Call<ClientEmpresaDTO> findCategoriaEmpresaTermo(@Header("X-Cidade-Id") Long idCidade, @QueryMap Map<String, Object> options);

    @GET("empresa/empresa/")
    Call<Empresa> findEmpresaDetalhes(@Header("X-Cidade-Id") Long idCidade, @QueryMap Map<String, Long> options);

   /* @POST("v1/empresa/atualizacao")
    Call<MensagemDTO> sendSolicitacao(@Header("X-Cidade-Id") String idCidade, @Body SolicitacaoRB solicitacao);

    @GET("v1/cidade/abrangencia")
    Call<List<AbrangenciaDTO>> findAbrangencia(@Header("X-Cidade-Id") String idCidade);

    @POST("v1/empresa/cadastro")
    Call<MensagemDTO> sendEmpresaCadastro(@Header("X-Cidade-Id") String idCidade, @Body CadastroEmpresaRB cadastro);

    @POST("empresa/{idEmpresa}/log")
    Call<Void> sendLogEmpresa(@Header("X-Cidade-Id") String idCidade, @Path("idEmpresa") Long id, @Body LogRB dispositivo);

    @GET("v1/cep/{numero}")
    Call<CepDTO> cep(@Header("X-Cidade-Id") String idCidade, @Path("numero") String numero);*/

}
