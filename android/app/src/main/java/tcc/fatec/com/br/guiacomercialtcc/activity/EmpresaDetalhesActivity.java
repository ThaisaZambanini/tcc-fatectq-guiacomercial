package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.client.Api;
import tcc.fatec.com.br.guiacomercialtcc.client.ClientApi;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;
import tcc.fatec.com.br.guiacomercialtcc.tabs.EmpresaDetalhesFragment;
import tcc.fatec.com.br.guiacomercialtcc.tabs.EmpresaFormaPagamentoFragment;
import tcc.fatec.com.br.guiacomercialtcc.tabs.EmpresaHorarioFragment;
import tcc.fatec.com.br.guiacomercialtcc.util.ExtraConstantes;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class EmpresaDetalhesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_info_red,
            R.drawable.ic_access_time_red,
            R.drawable.ic_card_red
    };
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_detalhes);
        Intent intent = getIntent();
        id = intent.getLongExtra(ExtraConstantes.ID, 0l);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        viewPager = findViewById(R.id.viewpager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Api api = ClientApi.getApi();
        Call<Empresa> call = api.findEmpresaDetalhes(id);
        call.enqueue(new Callback<Empresa>() {
            @Override
            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                if (response.isSuccessful()) {
                    final Empresa empresa = response.body();
                    setupViewPager(empresa);
                }
            }

            @Override
            public void onFailure(Call<Empresa> call, Throwable t) {
                Log.e("guiacomercialtcc ", t.getMessage());
            }
        });
    }

    private void setupViewPager(Empresa empresa) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(EmpresaDetalhesFragment.newInstance(empresa), "");

        if (empresa.getHorarios() != null && !empresa.getHorarios().isEmpty()) {
            adapter.addFragment(EmpresaHorarioFragment.newInstance(empresa), "");
        }

        if (empresa.getListaFormaPagamento() != null && !empresa.getListaFormaPagamento().isEmpty()) {
            adapter.addFragment(EmpresaFormaPagamentoFragment.newInstance(empresa), "");
        }

        viewPager.setAdapter(adapter);

        int count = 0;

        tabLayout.getTabAt(count++).setIcon(tabIcons[0]);

        if (empresa.getHorarios() != null && !empresa.getHorarios().isEmpty()) {
            tabLayout.getTabAt(count++).setIcon(tabIcons[1]);
        }

        if (empresa.getListaFormaPagamento() != null && !empresa.getListaFormaPagamento().isEmpty()) {
            tabLayout.getTabAt(count++).setIcon(tabIcons[2]);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
