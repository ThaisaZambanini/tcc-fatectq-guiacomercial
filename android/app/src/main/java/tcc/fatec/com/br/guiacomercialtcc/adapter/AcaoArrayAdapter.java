package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.to.BotaoAcaoTO;

public class AcaoArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<BotaoAcaoTO> listaContatos;

    public AcaoArrayAdapter(Context context, int resource, List<BotaoAcaoTO> listaContatos) {
        super(context, resource, listaContatos);
        this.context = context;
        this.listaContatos = listaContatos;
    }

    @Override
    public int getCount() {
        return listaContatos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.item_adapter_acao, parent, false);
        TextView txtDescricao = rowView.findViewById(R.id.txt_descricao);
        ImageView imgIcone = rowView.findViewById(R.id.img_icone);

        BotaoAcaoTO to = listaContatos.get(position);

        txtDescricao.setText(to.getNome());

        if (to.getIcone() == null) {
            imgIcone.setVisibility(View.GONE);
        } else {
            imgIcone.setImageResource(to.getIcone());
        }

        return rowView;
    }

}
