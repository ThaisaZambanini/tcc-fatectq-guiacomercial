package tcc.fatec.com.br.guiacomercialtcc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                CidadeDTO dto = SessaoUtil.getCidade(getApplicationContext());
                if (dto != null) {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this, EscolherCidadeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
