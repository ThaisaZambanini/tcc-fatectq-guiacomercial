package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.model.Imagem;

public class FotosCustomAdapter extends RecyclerView.Adapter<FotosCustomAdapter.FotoViewHolder> {

    private Context mContext;
    private List<Imagem> listaImagens;

    public FotosCustomAdapter(Context mContext, List<Imagem> listaImagens) {
        this.listaImagens = listaImagens;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_imagem_fresco, viewGroup, false);
        return new FotosCustomAdapter.FotoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder fotoViewHolder, int position) {
        Imagem imagem = listaImagens.get(position);

        Uri uri = Uri.parse(imagem.getImagem());
        fotoViewHolder.mImage.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return listaImagens.size();
    }

    public class FotoViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mImage;

        public FotoViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_fotos_detalhes_empresa);
        }
    }

}
