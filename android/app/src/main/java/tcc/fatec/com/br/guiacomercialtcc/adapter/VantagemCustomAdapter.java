package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.dto.VantagemDTO;

public class VantagemCustomAdapter extends RecyclerView.Adapter<VantagemCustomAdapter.VantagemViewHolder> {
    private Context mContext;
    private List<VantagemDTO> listaVantagem;

    public VantagemCustomAdapter(Context mContext, List<VantagemDTO> listaHorario) {
        this.mContext = mContext;
        this.listaVantagem = listaHorario;
    }

    @NonNull
    @Override
    public VantagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_vantagem, parent, false);
        return new VantagemCustomAdapter.VantagemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull VantagemViewHolder holder, int position) {
        VantagemDTO vantagemDTO = listaVantagem.get(holder.getAdapterPosition());
        holder.textoSimples.setText(vantagemDTO.getTexto());
    }

    @Override
    public int getItemCount() {
        return listaVantagem.size();
    }

    public class VantagemViewHolder extends RecyclerView.ViewHolder {
        public TextView textoSimples;

        public VantagemViewHolder(View itemView) {
            super(itemView);
            textoSimples = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
