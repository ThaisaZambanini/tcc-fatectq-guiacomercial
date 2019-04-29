package tcc.fatec.com.br.guiacomercialtcc.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.activity.EmpresaListaActivity;
import tcc.fatec.com.br.guiacomercialtcc.adapter.CategoriaAdapter;
import tcc.fatec.com.br.guiacomercialtcc.client.Api;
import tcc.fatec.com.br.guiacomercialtcc.client.ClientApi;
import tcc.fatec.com.br.guiacomercialtcc.commons.CidadeSessaoActivity;
import tcc.fatec.com.br.guiacomercialtcc.commons.RecyclerViewOnClickListenerHack;
import tcc.fatec.com.br.guiacomercialtcc.dto.CategoriaDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.ParametroBuscaDTO;
import tcc.fatec.com.br.guiacomercialtcc.util.Constantes;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class GuiaComercialFragment extends CidadeSessaoActivity implements RecyclerViewOnClickListenerHack {

    private RecyclerView categoriaRecyclerView;
    private EditText edt_busca;
    private AppCompatImageButton btn_buscar;
    private ProgressBar progressBar;
    private CardView card_nenhumResultado;
    private List<CategoriaDTO> listaCategoria;

    public GuiaComercialFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guia_comercial, container, false);

        progressBar = view.findViewById(R.id.progresso);

        card_nenhumResultado = view.findViewById(R.id.card_nenhumResultado);

        inicializaCidadeSessao(getActivity(), view);

        edt_busca = view.findViewById(R.id.edt_busca);
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
        edt_busca.clearFocus();

        btn_buscar = view.findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        carregaListaCategoria(view);
        categoriaRecyclerView.requestFocus();
        return view;
    }

    private void buscar() {
        String termoBusca = StringUtils.trimToNull(edt_busca.getText().toString());
        if (StringUtils.isBlank(termoBusca) || termoBusca.length() < Constantes.minimoCaracteresBusca) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
            ParametroBuscaDTO dto = new ParametroBuscaDTO();
            dto.setTermo(termoBusca);
            SessaoUtil.setParametrosBusca(getContext(), dto);

            Intent intent = new Intent(getActivity(), EmpresaListaActivity.class);
            startActivity(intent);

        }
    }

    private void carregaListaCategoria(View view) {
        progressBar.setVisibility(View.VISIBLE);

        categoriaRecyclerView = view.findViewById(R.id.categoria_recyclerView);
        categoriaRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Api api = ClientApi.getApi();
        CidadeDTO cidade = SessaoUtil.getCidade(getActivity());
        Call<List<CategoriaDTO>> call = api.findCategorias(cidade.getId());

        call.enqueue(new Callback<List<CategoriaDTO>>() {
            @Override
            public void onResponse(Call<List<CategoriaDTO>> call, Response<List<CategoriaDTO>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (listaCategoria != null) {
                        listaCategoria.clear();
                    }

                    listaCategoria = response.body();

                    if (listaCategoria != null) {
                        Integer columns = 3;
                        if (listaCategoria.size() < 8) {
                            columns = 2;
                        }
                        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), columns);
                        categoriaRecyclerView.setLayoutManager(mGridLayoutManager);

                        CategoriaAdapter myAdapter = new CategoriaAdapter(getActivity(), listaCategoria);
                        myAdapter.setRecyclerViewOnClickListenerHack(GuiaComercialFragment.this);
                        categoriaRecyclerView.setAdapter(myAdapter);
                        card_nenhumResultado.setVisibility(View.GONE);
                    }
                } else {
                    card_nenhumResultado.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaDTO>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickListener(View view, int position) {
        CategoriaDTO item = listaCategoria.get(position);

        ParametroBuscaDTO dto = new ParametroBuscaDTO();
        dto.setCategoria(item.getId());
        SessaoUtil.setParametrosBusca(getActivity().getApplicationContext(), dto);

        Intent intent = new Intent(getActivity(), EmpresaListaActivity.class);
        startActivity(intent);

    }
}
