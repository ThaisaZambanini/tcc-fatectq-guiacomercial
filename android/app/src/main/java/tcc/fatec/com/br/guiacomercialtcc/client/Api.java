package tcc.fatec.com.br.guiacomercialtcc.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.EstadoDTO;

public interface Api {

    @GET("estado/")
    Call<List<EstadoDTO>> findEstados();

    @GET("v1/cidade")
    Call<List<CidadeDTO>> findCidades(@Query("idEstado") Long idEstado);

   /*  @GET("v1/categoria")
    Call<List<CategoriaDTO>> findCategorias(@Header("X-Cidade-Id") String idCidade);

    @GET("v1/empresa")
    Call<ClientEmpresaDTO> findCategoriaEmpresaTermo(@Header("X-Cidade-Id") String idCidade, @QueryMap Map<String, Object> options);

    @GET("v1/empresa/{id}")
    Call<Empresa> findEmpresaDetalhes(@Header("X-Cidade-Id") String idCidade, @Path("id") Long id, @QueryMap Map<String, String> options);

    @POST("v1/empresa/atualizacao")
    Call<MensagemDTO> sendSolicitacao(@Header("X-Cidade-Id") String idCidade, @Body SolicitacaoRB solicitacao);

    @GET("v1/propaganda")
    Call<PropagandaDTO> findPropaganda(@Header("X-Cidade-Id") String idCidade);

    @GET("v1/franqueado")
    Call<FranqueadoDTO> findFranqueadoContato(@Header("X-Cidade-Id") String idCidade);

    @POST("v1/clubevantagem/acesso")
    Call<Usuario> sendLogin(@Header("X-Cidade-Id") String idCidade, @Body LoginRB loginRB);

    @GET("v1/cidade/abrangencia")
    Call<List<AbrangenciaDTO>> findAbrangencia(@Header("X-Cidade-Id") String idCidade);

    @GET("v1/clubevantagem/empresa/{cartao}")
    Call<ClientEmpresaDTO> findEmpresaClube(@Header("X-Cidade-Id") String idCidade, @Path("cartao") String cartao, @QueryMap Map<String, Object> options);

    @GET("v1/clubevantagem/empresa/{cartao}/{idEmpresa}")
    Call<Empresa> findEmpresaClubeDetalhes(@Header("X-Cidade-Id") String idCidade, @Path("cartao") String cartao, @Path("idEmpresa") Long id);

    @POST("v1/clubevantagem/cadastro")
    Call<MensagemDTO> sendCadastrarUsuario(@Header("X-Cidade-Id") String idCidade, @Body CadastroUsuarioRB cadastro);

    @POST("v1/clubevantagem/atualizacao")
    Call<MensagemDTO> sendAtualizarDados(@Header("X-Cidade-Id") String idCidade, @Body AtualizacaoUsuarioRB atualizacao);

    @POST("v1/empresa/cadastro")
    Call<MensagemDTO> sendEmpresaCadastro(@Header("X-Cidade-Id") String idCidade, @Body CadastroEmpresaRB cadastro);

    @POST("v1/dispositivo")
    @Headers({"Content-Type: application/json"})
    Call<Void> dispositivo(@Header("X-Cidade-Id") String idCidade, @Body DispositivoRB rb);

    @POST("clubevantagem/empresa/{idEmpresa}/log")
    Call<Void> sendLogClubeVantegem(@Header("X-Cidade-Id") String idCidade, @Path("idEmpresa") Long id, @Body LogRB dispositivo);

    @POST("empresa/{idEmpresa}/log")
    Call<Void> sendLogEmpresa(@Header("X-Cidade-Id") String idCidade, @Path("idEmpresa") Long id, @Body LogRB dispositivo);

    @GET("v1/cep/{numero}")
    Call<CepDTO> cep(@Header("X-Cidade-Id") String idCidade, @Path("numero") String numero);

    @POST("v1/clubevantagem/revalidar")
    Call<ClubeVantagemRevalidarDTO> clubeVantagemRevalidar(@Header("X-Cidade-Id") String idCidade, @Body LoginRB loginRB);*/

}
