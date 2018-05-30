package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.R;

public class Splash extends AppCompatActivity {

    Animation nubesAnimacion, carroAnimacion, textoAnimacion;
    LinearLayout textoLayout;
    FrameLayout nubesLayout, carroLayout;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    //Transition
    private Transition transition;
    //36610
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Inicializador();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CargarActivity();
            }
        },4000);
    }
    public void Inicializador(){
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        nubesLayout= (FrameLayout) findViewById(R.id.nubes_splash);
        carroLayout= (FrameLayout) findViewById(R.id.carro_splash);
        textoLayout= (LinearLayout) findViewById(R.id.texto_splash);
        AnimacionAcerosOcotlan();
    }
    public void CargarActivity(){
        Intent intentCodigoIngreso = new Intent(Splash.this,CodigoIngreso.class);
        Intent intentEncuesta = new Intent(Splash.this, EncuestaActivity.class);
        Intent intentProcesoEntrega = new Intent(Splash.this, ProgresoEntregaActivity.class);
        Intent intentVerOfertaEntrega = new Intent(Splash.this, VerOferta.class);
        if (!TextUtils.isEmpty(MetodosSharedPreference.ObtenerCodigoEntregaPref(prs))){
            startActivity(intentProcesoEntrega);
        }else{
            startActivity(intentCodigoIngreso);
            overridePendingTransition(R.anim.activity_transition_in,R.anim.activity_transition_in);
        }
        finish();
    }
    private void AnimacionAcerosOcotlan(){
        nubesAnimacion = AnimationUtils.loadAnimation(this,R.anim.nubes_splash);
        nubesLayout.setAnimation(nubesAnimacion);
        carroAnimacion = AnimationUtils.loadAnimation(this,R.anim.carro_splash);
        carroLayout.setAnimation(carroAnimacion);
        textoAnimacion = AnimationUtils.loadAnimation(this,R.anim.texto_splash);
        textoLayout.setAnimation(textoAnimacion);
    }
}
