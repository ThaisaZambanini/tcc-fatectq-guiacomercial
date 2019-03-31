package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.commons.RecyclerViewOnClickListenerHack;
import tcc.fatec.com.br.guiacomercialtcc.dto.CategoriaDTO;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private Context mContext;
    private List<CategoriaDTO> listaCategoria;
    private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;

    public CategoriaAdapter(Context mContext, List<CategoriaDTO> listaCategoria) {
        this.mContext = mContext;
        this.listaCategoria = listaCategoria;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_categoria, parent, false);
        return new CategoriaViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final CategoriaViewHolder holder, int position) {
        CategoriaDTO dto = listaCategoria.get(holder.getAdapterPosition());

        final Uri uri = Uri.parse(dto.getIcone());
        holder.mImage.setImageURI(uri);

        holder.mTitle.setText(dto.getNome());
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public SimpleDraweeView mImage;
        public TextView mTitle;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.imagem_categoria_card);
            mTitle = itemView.findViewById(R.id.nome_categoria_card);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListenerHack != null) {
                recyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        recyclerViewOnClickListenerHack = r;
    }
}
