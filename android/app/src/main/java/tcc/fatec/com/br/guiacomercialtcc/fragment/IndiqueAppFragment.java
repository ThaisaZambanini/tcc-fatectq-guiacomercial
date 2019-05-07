package tcc.fatec.com.br.guiacomercialtcc.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.commons.LinkAppsEnum;

public class IndiqueAppFragment extends Fragment {

    public IndiqueAppFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indique_app, container, false);

        final EditText edt_descricao = view.findViewById(R.id.edt_descricao);

        Button btn_indique = (Button) view.findViewById(R.id.btn_indique);
        btn_indique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String msg = edt_descricao.getText().toString();

                StringBuilder sb = new StringBuilder(msg);
                sb.append("\n");
                sb.append("*Site*: ").append(LinkAppsEnum.SITE.getDescricao());
                sb.append("\n");
                sb.append("*Android*: ").append(LinkAppsEnum.ANDROID.getDescricao());
                sb.append("\n");
                sb.append("*iOS*: ").append(LinkAppsEnum.IOS.getDescricao());

                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(sharingIntent, "Compartilhar..."));
            }
        });

        return view;
    }

}
