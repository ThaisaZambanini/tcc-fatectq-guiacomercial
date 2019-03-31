package tcc.fatec.com.br.guiacomercialtcc.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.model.Horario;

public class HorarioCustomAdapter extends RecyclerView.Adapter<HorarioCustomAdapter.HorarioViewHolder> {

    private Context mContext;
    private List<Horario> listaHorario;
    private Resources resources;

    public HorarioCustomAdapter(Context mContext, List<Horario> listaHorario, Resources resources) {
        this.mContext = mContext;
        this.listaHorario = listaHorario;
        this.resources = resources;
    }

    @NonNull
    @Override
    public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_horario_atendimento, parent, false);
        return new HorarioCustomAdapter.HorarioViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioViewHolder holder, int position) {
        Horario horario = listaHorario.get(holder.getAdapterPosition());

        holder.diaSemana.setText(horario.getDiaSemana());
        holder.horario.setText(horario.getTexto());

        if (horario.isSelecionado()) {
            holder.diaSemana.setTextColor(resources.getColor(R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return listaHorario.size();
    }

    public class HorarioViewHolder extends RecyclerView.ViewHolder {
        public TextView diaSemana;
        public TextView horario;

        public HorarioViewHolder(View itemView) {
            super(itemView);
            diaSemana = itemView.findViewById(R.id.text_dia_semana);
            horario = itemView.findViewById(R.id.text_horario_funcionamento);
        }
    }

}
