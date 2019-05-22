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
import tcc.fatec.com.br.guiacomercialtcc.adapter.CartaoAdapter;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;

public class EmpresaFormaPagamentoFragment extends Fragment {
    private static Empresa empresa;


    public EmpresaFormaPagamentoFragment() {
    }

    public static EmpresaFormaPagamentoFragment newInstance(Empresa param1) {
        EmpresaFormaPagamentoFragment fragment = new EmpresaFormaPagamentoFragment();
        empresa = param1;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa_forma_pagamento, container, false);

        final RecyclerView rv_cartoes_pagamento = view.findViewById(R.id.rv_cartoes_pagamento);
        rv_cartoes_pagamento.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        rv_cartoes_pagamento.setLayoutManager(mGridLayoutManager);

        TextView linearFormaPagamento = view.findViewById(R.id.txt_formaPagamento);
        if (empresa.getListaFormaPagamento().isEmpty()) {
            linearFormaPagamento.setVisibility(View.GONE);
        }

        CartaoAdapter adapterCartao = new CartaoAdapter(getActivity().getApplicationContext(), empresa.getListaFormaPagamento());
        rv_cartoes_pagamento.setAdapter(adapterCartao);

        return view;
    }

}
