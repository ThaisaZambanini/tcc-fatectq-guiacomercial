package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.model.FormaPagamento;

public class CartaoAdapter extends RecyclerView.Adapter<CartaoAdapter.CartaoViewHolder> {

    private Context mContext;
    private List<FormaPagamento> listaCartao;

    public CartaoAdapter(Context mContext, List<FormaPagamento> listaCartao) {
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
        FormaPagamento formaPagamento = listaCartao.get(position);
        holder.nome.setText(formaPagamento.getDescricao());
    }

    @Override
    public int getItemCount() {
        return listaCartao.size();
    }

    public class CartaoViewHolder extends RecyclerView.ViewHolder {
        public TextView nome;

        public CartaoViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
        }
    }

}
