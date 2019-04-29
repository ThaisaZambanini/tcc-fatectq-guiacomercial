package tcc.fatec.com.br.guiacomercialtcc.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.adapter.HorarioCustomAdapter;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;

public class EmpresaHorarioFragment extends Fragment {
    private static Empresa empresa;

    public EmpresaHorarioFragment() {
    }

    public static EmpresaHorarioFragment newInstance(Empresa param1) {
        EmpresaHorarioFragment fragment = new EmpresaHorarioFragment();
        empresa = param1;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_empresa_horario, container, false);
        final RecyclerView rv_horario_funcionamento = view.findViewById(R.id.rv_horario_funcionamento);

        HorarioCustomAdapter adapterHorario = new HorarioCustomAdapter(getActivity().getApplicationContext(), empresa.getHorarios(), getResources());
        GridLayoutManager mGridLayoutHorario = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        mGridLayoutHorario.setSmoothScrollbarEnabled(false);
        rv_horario_funcionamento.setHasFixedSize(true);
        rv_horario_funcionamento.setNestedScrollingEnabled(false);
        rv_horario_funcionamento.setAdapter(adapterHorario);
        rv_horario_funcionamento.setLayoutManager(mGridLayoutHorario);

        TextView layout_horarios = view.findViewById(R.id.txt_horarios);
        if (empresa.getHorarios().isEmpty()) {
            layout_horarios.setVisibility(View.GONE);
        }
        rv_horario_funcionamento.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

}
