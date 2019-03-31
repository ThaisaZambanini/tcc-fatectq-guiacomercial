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
import tcc.fatec.com.br.guiacomercialtcc.model.Cartao;

public class CartaoAdapter extends RecyclerView.Adapter<CartaoAdapter.CartaoViewHolder> {

    private Context mContext;
    private List<Cartao> listaCartao;

    public CartaoAdapter(Context mContext, List<Cartao> listaCartao) {
        this.mContext = mContext;
        this.listaCartao = listaCartao;
    }

    @NonNull
    @Override
    public CartaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_cartao, parent, false);
        return new CartaoAdapter.CartaoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaoViewHolder holder, int position) {
        Cartao cartao = listaCartao.get(position);

        Uri uri = Uri.parse(cartao.getIcone());
        holder.mImage.setImageURI(uri);
        holder.nome.setText(cartao.getNome());
    }

    @Override
    public int getItemCount() {
        return listaCartao.size();
    }

    public class CartaoViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mImage;
        public TextView nome;

        public CartaoViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.imagem_cartao);
            nome = itemView.findViewById(R.id.nome);
        }
    }

}
