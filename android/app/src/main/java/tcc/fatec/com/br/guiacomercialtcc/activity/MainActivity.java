package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yayandroid.locationmanager.LocationManager;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.listener.LocationListener;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.commons.RecyclerViewOnClickListenerHack;
import tcc.fatec.com.br.guiacomercialtcc.dto.GeoLocalizacaoDTO;
import tcc.fatec.com.br.guiacomercialtcc.fragment.GuiaComercialFragment;
import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;
import tcc.fatec.com.br.guiacomercialtcc.util.Constantes;
import tcc.fatec.com.br.guiacomercialtcc.util.GeralUtil;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewOnClickListenerHack {
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private LocationManager awesomeLocationManager;

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

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        boolean isAutorizaGPS = sharedPreferences.getBoolean(Constantes.CONFIG_PERMITE_GPS, true);

        if (isAutorizaGPS) {

            awesomeLocationManager = new LocationManager.Builder(getApplicationContext())
                    .activity(this) // Only required to ask permission and/or GoogleApi - SettingsApi
                    .configuration(Configurations.defaultConfiguration("ABC", "DEF"))
                    .notify(new LocationListener() {
                        @Override
                        public void onProcessTypeChanged(int processType) {
                        }

                        @Override
                        public void onLocationChanged(Location location) {
                            handleNewLocation(location);
                        }

                        @Override
                        public void onLocationFailed(int type) {
                        }

                        @Override
                        public void onPermissionGranted(boolean alreadyHadPermission) {
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                        }
                    })
                    .build();

        } else {
            handleNewLocation(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:
                        SharedPreferences sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        sharedPreferences.edit().putBoolean(Constantes.CONFIG_PERMITE_GPS, true).apply();
                        break;
                    case RESULT_CANCELED:
                        SharedPreferences sharedPreferences2 =
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        sharedPreferences2.edit().putBoolean(Constantes.CONFIG_PERMITE_GPS, false).apply();
                        SessaoUtil.setGeoLocalizacao(getApplicationContext(), null);
                        Log.e("Settings", "Result Cancel");
                        break;
                }
                break;
        }
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
        } else if (id == R.id.nav_clubeVantagens) {
            Usuario usuario = SessaoUtil.getUsuario(getApplicationContext());
            /*if (usuario != null && usuario.isLembrarLogin()) {
                fragment = ClubeInicioFragment.newInstance(false);
            } else {
                fragment = ClubeLoginFragment.newInstance(false);
            }*/
        } else if (id == R.id.nav_indicar) {
            //  fragment = new IndiqueAppFragment();
        } else if (id == R.id.nav_cadastrarEmpresa) {
            // fragment = new CadastroEmpresaFragment();
        } else if (id == R.id.nav_contato) {
            //  fragment = new ContatoFragment();
        } else if (id == R.id.nav_settings) {
            // startActivity(new Intent(MainActivity.this, ConfiguracaoActivity.class));
            finish();
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

    private void handleNewLocation(final Location location) {
        if (location != null) {
            SessaoUtil.setGeoLocalizacao(getApplicationContext(), new GeoLocalizacaoDTO(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
        } else {
            SessaoUtil.setGeoLocalizacao(getApplicationContext(), null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (awesomeLocationManager != null) {
            awesomeLocationManager.onResume();
            awesomeLocationManager.get();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (awesomeLocationManager != null) {
            awesomeLocationManager.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (awesomeLocationManager != null) {
            awesomeLocationManager.onDestroy();
        }
    }
}
