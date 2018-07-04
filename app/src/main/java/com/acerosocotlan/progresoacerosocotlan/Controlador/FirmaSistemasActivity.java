package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.R;

public class FirmaSistemasActivity extends AppCompatActivity {

    private ImageView
            firma_simba_cola, firma_simba,
            firma_simba_gracias,
            firma_azul0_simba,firma_azul1_simba,
            firma_rojo1_simba, firma_rojo0_simba,
            firma_verde1_simba, firma_verde0_simba,
            firma_amarillo1_simba,firma_amarillo0_simba;

    private Animation numero1Animacion, numero0Animacion, cola_simbaAnimacion, creditosAnimacion;
    private LinearLayout firma_linearlayout_creditos;
    private FrameLayout firma_layoout_filtro_credito;
    private int contador=0;
    private FloatingActionButton btn_fab_creditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_sistemas);
        IniciadorView();
        firma_simba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimacionSimba();
            }
        });

        btn_fab_creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AnimacionCreditos();
            }
        });
    }

    private void AnimacionSimba() {
        if(contador<5) {
            //AZUL
            firma_azul0_simba.setVisibility(View.VISIBLE);
            numero0Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero0_animation);
            firma_azul0_simba.setAnimation(numero0Animacion);
            firma_azul0_simba.setVisibility(View.INVISIBLE);

            firma_azul1_simba.setVisibility(View.VISIBLE);
            numero0Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero0_animation);
            firma_azul1_simba.setAnimation(numero0Animacion);
            firma_azul1_simba.setVisibility(View.INVISIBLE);

            //VERDE
            firma_verde0_simba.setVisibility(View.VISIBLE);
            numero0Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero0_animation);
            firma_verde0_simba.setAnimation(numero0Animacion);
            firma_verde0_simba.setVisibility(View.INVISIBLE);

            firma_verde1_simba.setVisibility(View.VISIBLE);
            numero0Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero0_animation);
            firma_verde1_simba.setAnimation(numero0Animacion);
            firma_verde1_simba.setVisibility(View.INVISIBLE);

            //ROJO
            firma_rojo0_simba.setVisibility(View.VISIBLE);
            numero1Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero1_animation);
            firma_rojo0_simba.setAnimation(numero1Animacion);
            firma_rojo0_simba.setVisibility(View.INVISIBLE);

            firma_rojo1_simba.setVisibility(View.VISIBLE);
            numero1Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero1_animation);
            firma_rojo1_simba.setAnimation(numero1Animacion);
            firma_rojo1_simba.setVisibility(View.INVISIBLE);

            //AMARILLO
            firma_amarillo0_simba.setVisibility(View.VISIBLE);
            numero1Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero1_animation);
            firma_amarillo0_simba.setAnimation(numero1Animacion);
            firma_amarillo0_simba.setVisibility(View.INVISIBLE);

            firma_amarillo1_simba.setVisibility(View.VISIBLE);
            numero1Animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.numero1_animation);
            firma_amarillo1_simba.setAnimation(numero1Animacion);
            firma_amarillo1_simba.setVisibility(View.INVISIBLE);

            cola_simbaAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cola_simaba_animation);
            firma_simba_cola.setAnimation(cola_simbaAnimacion);

            contador = contador + 1;
        }else{

            firma_simba_gracias.setVisibility(View.VISIBLE);
            cola_simbaAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cola_simaba_animation);
            firma_simba_cola.setAnimation(cola_simbaAnimacion);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    contador=0;
                    firma_simba_gracias.setVisibility(View.INVISIBLE);
                }
            },2000);
        }
    }
    private void AnimacionCreditos() {
        btn_fab_creditos.setEnabled(false);
        firma_layoout_filtro_credito.setVisibility(View.VISIBLE);
        firma_linearlayout_creditos.setVisibility(View.VISIBLE);
        creditosAnimacion = AnimationUtils.loadAnimation(FirmaSistemasActivity.this, R.anim.creditos_animacion);
        firma_linearlayout_creditos.setAnimation(creditosAnimacion);
        firma_linearlayout_creditos.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firma_layoout_filtro_credito.setVisibility(View.INVISIBLE);
                btn_fab_creditos.setEnabled(true);
            }
        },11000);

    }
    private void IniciadorView() {
        btn_fab_creditos = (FloatingActionButton) findViewById(R.id.btn_fab_mostrar_creditos);

        firma_simba_cola  = (ImageView) findViewById(R.id.firma_simba_cola);
        firma_simba = (ImageView) findViewById(R.id.firma_simba);
        firma_simba_gracias = (ImageView) findViewById(R.id.firma_simba_gracias);

        firma_azul0_simba = (ImageView) findViewById(R.id.firma_azul0_simba);
        firma_azul1_simba = (ImageView) findViewById(R.id.firma_azul1_simba);
        firma_rojo1_simba = (ImageView) findViewById(R.id.firma_rojo1_simba);
        firma_rojo0_simba = (ImageView) findViewById(R.id.firma_rojo0_simba);
        firma_verde1_simba = (ImageView) findViewById(R.id.firma_verde1_simba);
        firma_verde0_simba  = (ImageView) findViewById(R.id.firma_verde0_simba);
        firma_amarillo1_simba  = (ImageView) findViewById(R.id.firma_amarillo1_simba);
        firma_amarillo0_simba  = (ImageView) findViewById(R.id.firma_amarillo0_simba);
        firma_linearlayout_creditos = (LinearLayout)findViewById(R.id.firma_linearlayout_creditos);
        firma_layoout_filtro_credito  = (FrameLayout) findViewById(R.id.firma_layoout_filtro_credito);
    }
}
