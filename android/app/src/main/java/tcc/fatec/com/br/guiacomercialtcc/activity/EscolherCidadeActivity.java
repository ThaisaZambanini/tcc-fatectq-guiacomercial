package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.client.Api;
import tcc.fatec.com.br.guiacomercialtcc.client.ClientApi;
import tcc.fatec.com.br.guiacomercialtcc.commons.SpinnerArrayAdapter;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.dto.EstadoDTO;
import tcc.fatec.com.br.guiacomercialtcc.rb.DispositivoRB;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class EscolherCidadeActivity extends AppCompatActivity {

    private List<EstadoDTO> listaEstados;
    private List<CidadeDTO> listaCidades;
    private Spinner cidade;
    private Dialog mDialog;
    private CidadeDTO dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_cidade);

        createDialog();

        Spinner estado = findViewById(R.id.estado_spinner);
        cidade = findViewById(R.id.cidade_spinner);

        AppCompatButton botaoContinuar = findViewById(R.id.btn_continuar);
        botaoContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dto == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("Escolha uma Cidade antes de continuar.")
                            .setTitle("Aviso")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    mDialog.show();
                    SessaoUtil.setCidade(getApplicationContext(), dto);
                    Intent intent = new Intent(EscolherCidadeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        listaEstados = new ArrayList<>(0);
        listaCidades = new ArrayList<>(0);

        EstadoDTO estadoDto = new EstadoDTO(0L, "Selecione");
        listaEstados.add(estadoDto);

        CidadeDTO cidadeDto = new CidadeDTO();
        cidadeDto.setNome("Selecione");
        listaCidades.add(cidadeDto);

        carregaEstado(estado);
        carregaCidade();
    }

    public void carregaEstado(final Spinner estado) {
        mDialog.show();

        Api api = ClientApi.getApi();
        Call<List<EstadoDTO>> call = api.findEstados();
        call.enqueue(new Callback<List<EstadoDTO>>() {
            @Override
            public void onResponse(Call<List<EstadoDTO>> call, Response<List<EstadoDTO>> response) {
                if (response.isSuccessful()) {
                    listaEstados.addAll(response.body());

                    ArrayAdapter<EstadoDTO> adapter = new SpinnerArrayAdapter<EstadoDTO>(getApplicationContext(), R.layout.spinner_padding, listaEstados) {
                        @Override
                        public boolean isEnabled(int position) {
                            if (position == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    };

                    estado.setAdapter(adapter);
                    verificaCidadeSessao(estado);

                    mDialog.dismiss();

                    estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position != 0) {
                                mDialog.show();

                                EstadoDTO estadoDto = (EstadoDTO) parent.getItemAtPosition(position);
                                Api api = ClientApi.getApi();
                                Call<List<CidadeDTO>> call = api.findCidades(estadoDto.getId());
                                call.enqueue(new Callback<List<CidadeDTO>>() {
                                    @Override
                                    public void onResponse(Call<List<CidadeDTO>> call, Response<List<CidadeDTO>> response) {
                                        if (response.isSuccessful()) {
                                            listaCidades.clear();
                                            CidadeDTO cidadeDTO = new CidadeDTO();
                                            cidadeDTO.setNome("Selecione");
                                            listaCidades.add(cidadeDTO);
                                            listaCidades.addAll(response.body());
                                            mDialog.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<CidadeDTO>> call, Throwable t) {
                                        mDialog.dismiss();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<EstadoDTO>> call, Throwable t) {
                mDialog.dismiss();
            }
        });
    }

    private void verificaCidadeSessao(Spinner estado) {
        CidadeDTO cidadeSessao = SessaoUtil.getCidade(getApplicationContext());
        if (cidadeSessao != null) {
            SessaoUtil.limpar(getApplicationContext());
            for (int i = 0; i < listaEstados.size(); i++) {
                if (listaEstados.get(i).getNome().equals(cidadeSessao.getEstadoDTO().getNome())) {
                    estado.setSelection(i);
                }
            }
        }
    }

    public void carregaCidade() {
        ArrayAdapter<CidadeDTO> adapter = new SpinnerArrayAdapter<CidadeDTO>(getApplicationContext(),
                R.layout.spinner_padding, listaCidades) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        cidade.setAdapter(adapter);
        cidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    dto = (CidadeDTO) parent.getItemAtPosition(position);
                } else {
                    dto = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void createDialog() {
        mDialog = new Dialog(EscolherCidadeActivity.this, android.R.style.Theme_Translucent);
        mDialog.setContentView(R.layout.dialog);
        mDialog.setCancelable(false);
    }
}
