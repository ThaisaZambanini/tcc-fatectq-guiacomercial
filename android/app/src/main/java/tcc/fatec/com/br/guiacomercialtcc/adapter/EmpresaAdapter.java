package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.dto.EmpresaBuscaDTO;

public abstract class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.MyViewHolder> {

    private Context context;
    private List<EmpresaBuscaDTO> listaEmpresas;

    public EmpresaAdapter(Context context, List<EmpresaBuscaDTO> listaEmpresas) {
        this.context = context;
        this.listaEmpresas = listaEmpresas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_empresa, parent, false);
        return new EmpresaAdapter.MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        EmpresaBuscaDTO dto = listaEmpresas.get(viewHolder.getAdapterPosition());
        final Uri uri = Uri.parse(dto.getLogo());

        viewHolder.mImage.setImageURI(uri);
        viewHolder.mNomeEmpresa.setText(dto.getNome());
        viewHolder.mCategoria.setText(dto.getCategoria());
        viewHolder.mEndereco1.setText(dto.getEndereco().getLinha1());
        viewHolder.mEndereco2.setText(dto.getEndereco().getLinha2());
        viewHolder.mEndereco3.setText(dto.getEndereco().getLinha3());
        viewHolder.mLocalizacao.setText(dto.getDistancia());

        if (dto.getEndereco().getLinha3() == null || dto.getEndereco().getLinha3().equals("")) {
            viewHolder.mEndereco3.setVisibility(View.GONE);
        }

        if (dto.getDistancia() == null || dto.getDistancia().equals("")) {
            viewHolder.layout_local.setVisibility(View.GONE);
        }

        if (!dto.isClubeVantagens()) {
            viewHolder.mBotaoClubeVantagens.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mNomeEmpresa;
        public TextView mEndereco1;
        public TextView mEndereco2;
        public TextView mEndereco3;
        public TextView mCategoria;
        public TextView mLocalizacao;
        public Button mBotaoDetalhes;
        public Button mBotaoClubeVantagens;
        public LinearLayout layout_local;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image_empresa_categoria);
            mNomeEmpresa = itemView.findViewById(R.id.text_nome_empresa_categoria);
            mEndereco1 = itemView.findViewById(R.id.text_endereco1_categoria);
            mEndereco2 = itemView.findViewById(R.id.text_endereco2_categoria);
            mEndereco3 = itemView.findViewById(R.id.text_endereco3_categoria);
            mCategoria = itemView.findViewById(R.id.text_categoria);
            mBotaoDetalhes = itemView.findViewById(R.id.btn_detalhes_empresa);
            mLocalizacao = itemView.findViewById(R.id.text_localizacao);
            layout_local = itemView.findViewById(R.id.layout_local);

            mBotaoDetalhes = itemView.findViewById(R.id.btn_detalhes_empresa);
            mBotaoDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmpresaBuscaDTO dto = listaEmpresas.get(getAdapterPosition());
                    abrir(dto);
                }
            });

            mBotaoClubeVantagens = itemView.findViewById(R.id.btn_clubeVantagens_empresa);
            mBotaoClubeVantagens.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmpresaBuscaDTO dto = listaEmpresas.get(getAdapterPosition());
                    clubeVantagens(dto);
                }
            });
        }
    }

    public abstract void abrir(EmpresaBuscaDTO dto);

    public abstract void clubeVantagens(EmpresaBuscaDTO dto);

}
