package tcc.fatec.com.br.guiacomercialtcc.commons;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class CidadeSessaoActivity extends Fragment {

    public void inicializaCidadeSessao(Context context, View view) {
        CidadeDTO cidadeDTO = SessaoUtil.getCidade(context);
       // ((TextView) view.findViewById(R.id.id_cidade_sessao)).setText(new StringBuilder(cidadeDTO.getNome()).append("-").append(cidadeDTO.getEstado()).toString());
    }
}
