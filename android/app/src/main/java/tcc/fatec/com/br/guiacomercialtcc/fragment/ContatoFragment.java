package tcc.fatec.com.br.guiacomercialtcc.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.client.Api;
import tcc.fatec.com.br.guiacomercialtcc.client.ClientApi;
import tcc.fatec.com.br.guiacomercialtcc.commons.AppUtils;
import tcc.fatec.com.br.guiacomercialtcc.commons.MaskUtils;
import tcc.fatec.com.br.guiacomercialtcc.dto.MensagemDTO;
import tcc.fatec.com.br.guiacomercialtcc.enums.TipoMascara;
import tcc.fatec.com.br.guiacomercialtcc.model.Contato;
import tcc.fatec.com.br.guiacomercialtcc.util.GeralUtil;

public class ContatoFragment extends Fragment {

    public ContatoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato, container, false);

        final EditText edt_telefone = view.findViewById(R.id.edt_celular);
        MaskUtils.setMaksInput(edt_telefone, TipoMascara.TELEFONE_CELULAR);

        final EditText edt_nome = view.findViewById(R.id.edt_nome);

        final EditText edt_email = view.findViewById(R.id.edt_email);

        final EditText edt_assunto = view.findViewById(R.id.edt_assunto);

        final EditText edt_mensagem = view.findViewById(R.id.edt_mensagem);

        Button btn_enviar = view.findViewById(R.id.btn_enviar_contato);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValido = true;

                if (AppUtils.isEmpty(edt_nome)) {
                    isValido = false;
                    AppUtils.setError(edt_nome, "Nome é obrigatório");
                } else {
                    AppUtils.clearError(edt_nome);
                }

                if (AppUtils.isEmpty(edt_telefone)) {
                    isValido = false;
                    AppUtils.setError(edt_telefone, "Telefone é obrigatório");
                } else {
                    AppUtils.clearError(edt_telefone);
                }

                if (AppUtils.isEmpty(edt_email)) {
                    isValido = false;
                    AppUtils.setError(edt_email, "E-mail é obrigatório");
                } else {
                    AppUtils.clearError(edt_email);
                }

                if (AppUtils.isEmpty(edt_assunto)) {
                    isValido = false;
                    AppUtils.setError(edt_assunto, "Assunto é obrigatório");
                } else {
                    AppUtils.clearError(edt_assunto);
                }

                if (AppUtils.isEmpty(edt_mensagem)) {
                    isValido = false;
                    AppUtils.setError(edt_mensagem, "Mensagem é obrigatória");
                } else {
                    AppUtils.clearError(edt_mensagem);
                }

                if (isValido) {
                    insereDados(edt_nome, edt_email, edt_assunto, edt_mensagem, edt_telefone);
                }
            }
        });

        return view;
    }

    private void insereDados(EditText edt_nome, EditText edt_email, EditText edt_assunto, EditText edt_mensagem, EditText edt_telefone) {
        Contato solicitacao = new Contato();
        solicitacao.setNome(edt_nome.getText().toString());
        solicitacao.setEmail(edt_email.getText().toString());
        solicitacao.setTelefone(edt_telefone.getText().toString());
        solicitacao.setAssunto(edt_assunto.getText().toString());
        solicitacao.setMensagem(edt_mensagem.getText().toString());

        Api api = ClientApi.getApi();
        Call<MensagemDTO> call = api.sendSolicitacaoContato(solicitacao);
        call.enqueue(new Callback<MensagemDTO>() {
            @Override
            public void onResponse(Call<MensagemDTO> call, Response<MensagemDTO> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(response.body().getMensagem())
                            .setTitle(response.body().getTitulo())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    GeralUtil.hideKeyboard(getActivity());
                                    GuiaComercialFragment fragment = new GuiaComercialFragment();
                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.include, fragment).commit();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    Log.e("GUIACOMERCIAL", "Erro: " + response.code());
                    Toast.makeText(getActivity(), "Ocorreu um erro ao salvar os dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MensagemDTO> call, Throwable t) {
                Log.e("GUIACOMERCIAL", "Erro: " + t.getStackTrace());
                Toast.makeText(getActivity(), "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
