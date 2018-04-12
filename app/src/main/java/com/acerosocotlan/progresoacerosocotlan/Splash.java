package com.acerosocotlan.progresoacerosocotlan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {

    Animation nubesAnimacion, carroAnimacion, textoAnimacion, cardviewAnimacion;
    LinearLayout textoLayout, cardview_folio_entrega;
    FrameLayout nubesLayout, carroLayout;
    Button boton_enviar_folio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Inicializador();
        boton_enviar_folio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splash.this, ProgresoEntregaActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
    public void Inicializador(){
        nubesLayout= (FrameLayout) findViewById(R.id.nubes_splash);
        carroLayout= (FrameLayout) findViewById(R.id.carro_splash);
        textoLayout= (LinearLayout) findViewById(R.id.texto_splash);
        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        cardview_folio_entrega= (LinearLayout) findViewById(R.id.cardview_edittext);
        nubesAnimacion = AnimationUtils.loadAnimation(this,R.anim.nubes_splash);
        nubesLayout.setAnimation(nubesAnimacion);
        carroAnimacion = AnimationUtils.loadAnimation(this,R.anim.carro_splash);
        carroLayout.setAnimation(carroAnimacion);
        textoAnimacion = AnimationUtils.loadAnimation(this,R.anim.texto_splash);
        textoLayout.setAnimation(textoAnimacion);
        cardviewAnimacion= AnimationUtils.loadAnimation(this,R.anim.cardview_splash);
        cardview_folio_entrega.setAnimation(cardviewAnimacion);
    }
}
