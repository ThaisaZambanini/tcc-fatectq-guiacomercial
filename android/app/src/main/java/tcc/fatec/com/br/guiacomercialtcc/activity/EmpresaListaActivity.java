package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.adapter.EmpresaAdapter;
import tcc.fatec.com.br.guiacomercialtcc.client.ClientApi;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ClientEmpresaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.EmpresaBuscaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.GeoLocalizacaoDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ParametroBuscaDTO;
import tcc.fatec.com.br.guiacomercialtcc.util.Constantes;
import tcc.fatec.com.br.guiacomercialtcc.util.ExtraConstantes;
import tcc.fatec.com.br.guiacomercialtcc.util.GeralUtil;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class EmpresaListaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmpresas;
    private List<EmpresaBuscaDTO> listaEmpresas;
    private EmpresaAdapter adapter;
    private EditText edt_busca;
    private AppCompatImageButton btn_buscar;
    private TextView text_quantidade_itens;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int PAGE_START = 0;
    private ParametroBuscaDTO parametros;
    private GeoLocalizacaoDTO geo;
    private Dialog mDialog;
    private CidadeDTO cidade;

    public EmpresaListaActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_lista);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        parametros = SessaoUtil.getParametrosBusca(getApplicationContext());
        geo = SessaoUtil.getGeoLocalizacao(getApplicationContext());
        cidade = SessaoUtil.getCidade(getApplicationContext());

        text_quantidade_itens = findViewById(R.id.text_quantidade_itens);
        edt_busca = findViewById(R.id.edt_busca);

        if (StringUtils.isNotBlank(parametros.getTermo())) {
            edt_busca.setText(parametros.getTermo());
        }

        edt_busca.clearFocus();

        createDialog();

        CidadeDTO cidadeDTO = SessaoUtil.getCidade(getApplicationContext());
        ((TextView) findViewById(R.id.id_cidade_sessao)).setText(new StringBuilder(cidadeDTO.getNome()).append("-").append(cidadeDTO.getEstadoDTO().getNome()).toString());

        recyclerViewEmpresas = findViewById(R.id.empresa_recyclerView);
        recyclerViewEmpresas.setHasFixedSize(true);
        recyclerViewEmpresas.requestFocus();

        listaEmpresas = new ArrayList<>();

        recyclerViewEmpresas.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && !isLastPage) {
                    if (!recyclerView.canScrollVertically(1)) {
                        isLoading = true;

                        PAGE_START = PAGE_START + 1;

                        buscaEmpresas();
                    }
                }
            }
        });

        buscaEmpresas();

        btn_buscar = findViewById(R.id.btn_buscar);
        edt_busca.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    buscar();
                    return true;
                }
                return false;
            }
        });
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }

    public void createDialog() {
        mDialog = new Dialog(EmpresaListaActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setContentView(R.layout.dialog);
        mDialog.setCancelable(false);
    }

    private void buscar() {
        String termoBusca = StringUtils.trimToNull(edt_busca.getText().toString());
        if (StringUtils.isBlank(termoBusca) || termoBusca.length() < Constantes.minimoCaracteresBusca) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(String.format("Digite pelo menos %s caracteres para pesquisar.", Constantes.minimoCaracteresBusca))
                    .setTitle("Aviso")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            edt_busca.requestFocus();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else {

            GeralUtil.hideKeyboard(this);

            PAGE_START = 0;
            isLoading = true;
            isLastPage = false;

            parametros = new ParametroBuscaDTO();
            parametros.setTermo(termoBusca);
            SessaoUtil.setParametrosBusca(getApplicationContext(), parametros);

            listaEmpresas.clear();
            adapter.notifyDataSetChanged();

            buscaEmpresas();

        }
    }

    private void buscaEmpresas() {
        Map<String, Object> options = new HashMap<>();
        options.put("paginaAtual", PAGE_START);
        options.put("paginaLimite", Constantes.limitePagina);

        if (parametros.getCategoria() != null && parametros.getCategoria() != 0) {
            options.put("idCategoria", parametros.getCategoria());
        }
        if (StringUtils.isNotBlank(parametros.getTermo())) {
            options.put("termo", parametros.getTermo());
        }

        mDialog.show();

        Call<ClientEmpresaDTO> call = ClientApi.getApi().findCategoriaEmpresaTermo(cidade.getId(), options);
        call.enqueue(new Callback<ClientEmpresaDTO>() {
            @Override
            public void onResponse(Call<ClientEmpresaDTO> call, Response<ClientEmpresaDTO> response) {
                if (response.isSuccessful()) {
                    ClientEmpresaDTO body = response.body();

                    if (body.getEmpresas() != null) {
                        listaEmpresas.addAll(body.getEmpresas());
                    }

                    StringBuilder text_quantidade = new StringBuilder();
                    if (body.getTotal() > 0) {
                        text_quantidade.append("Exibindo ").append(body.getTotal()).append(" Empresa(s)");
                    } else {
                        text_quantidade.append("Nenhuma empresa disponível.");
                    }
                    text_quantidade_itens.setText(text_quantidade.toString());

                    if (adapter == null) {

                        final LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerViewEmpresas.setItemAnimator(new DefaultItemAnimator());
                        adapter = new EmpresaAdapter(getApplicationContext(), listaEmpresas) {
                            @Override
                            public void abrir(EmpresaBuscaDTO dto) {
                                EmpresaListaActivity.this.abrir(dto);
                            }
                        };

                        adapter.notifyItemInserted(listaEmpresas.size());

                        recyclerViewEmpresas.setAdapter(adapter);
                        recyclerViewEmpresas.setLayoutManager(manager);

                    } else {

                        adapter.notifyItemInserted(listaEmpresas.size());

                    }

                    isLastPage = !body.isMais();
                    isLoading = false;

                } else {
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                }

                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ClientEmpresaDTO> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrir(EmpresaBuscaDTO dto) {
        Intent intent = new Intent(getApplicationContext(), EmpresaDetalhesActivity.class);
        intent.putExtra(ExtraConstantes.ID, dto.getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        EmpresaListaActivity.this.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
