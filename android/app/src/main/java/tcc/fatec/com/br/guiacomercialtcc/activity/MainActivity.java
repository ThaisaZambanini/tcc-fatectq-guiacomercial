package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.commons.RecyclerViewOnClickListenerHack;
import tcc.fatec.com.br.guiacomercialtcc.fragment.ContatoFragment;
import tcc.fatec.com.br.guiacomercialtcc.fragment.GuiaComercialFragment;
import tcc.fatec.com.br.guiacomercialtcc.fragment.IndiqueAppFragment;
import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;
import tcc.fatec.com.br.guiacomercialtcc.util.GeralUtil;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewOnClickListenerHack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        int position = getIntent().getIntExtra("position", 0);
        navigationView.setCheckedItem(position);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.include, new GuiaComercialFragment());
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.atencao)
                    .setMessage(R.string.mensagem_encerrar)
                    .setIcon(null)
                    .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(R.string.nao, null).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        GeralUtil.hideKeyboard(MainActivity.this);

        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_guiaComercial) {
            fragment = new GuiaComercialFragment();
        } else if (id == R.id.nav_indicar) {
            fragment = new IndiqueAppFragment();
        } else if (id == R.id.nav_contato) {
            fragment = new ContatoFragment();
        } else if (id == R.id.nav_trocar) {
            new AlertDialog.Builder(this)
                    .setTitle(getText(R.string.trocarCidade))
                    .setMessage("Tem certeza que deseja escolher outra Cidade?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MainActivity.this, EscolherCidadeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).show();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.include, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        setTitle(item.getTitle());
        return true;
    }

    @Override
    public void onClickListener(View view, int position) {
    }
}
