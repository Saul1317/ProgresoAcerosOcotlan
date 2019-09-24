package com.acerosocotlan.progresoacerosocotlan.Controlador.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.acerosocotlan.progresoacerosocotlan.Controlador.MenuPrincipal.MenuPrincipal;
import com.acerosocotlan.progresoacerosocotlan.R;

public class Splash extends AppCompatActivity {


    private Animation nubesAnimacion, carroAnimacion, textoAnimacion;
    private LinearLayout textoLayout;
    private FrameLayout nubesLayout, carroLayout;
    //SHARED PREFERENCE
    //private SharedPreferences prs;
    //36610
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //SE HACE LA REFERENCIA DE LOS COMPONENTES E INICIA LA ANIMACIÓN

        //METODO QUE SE EJECUTARÁ DESPUES DE 3 SEGUNDOS(3000 MILISEGUNDOS)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SE ABRE LA SIGUIENTE VENTANA
                CargarActivity();
            }
        },2000);
    }
    public void Inicializador(){
        //prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        //REFERENCIA DE LOS VIEWS
        /*nubesLayout= (FrameLayout) findViewById(R.id.nubes_splash);
        carroLayout= (FrameLayout) findViewById(R.id.carro_splash);
        textoLayout= (LinearLayout) findViewById(R.id.texto_splash);*/
        //EJECUTA LA ANIMACION INICIAL
        AnimacionAcerosOcotlan();
    }
    public void CargarActivity(){
        //SE HACE REFERENCIA A LA SIGUIENTE CLASE MENUOPCIONES.CLASS
        Intent intent = new Intent(Splash.this, MenuPrincipal.class);
        //ESTA LINEA DE CODIGO SIRVE PARA QUE EL USUARIO AL MOMENTO DE PRESIONAR EL BOTON DE REGRESAR NO PUEDA REGRESARSE A ESTA CLASE
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //SE INICIA LA SIGUIENTE ACTIVIDAD
        startActivity(intent);
    }

    /*
    * METODO QUE ASIGNA LA ANIMACION A LOS COMPONENTES
    */
    private void AnimacionAcerosOcotlan(){
        nubesAnimacion = AnimationUtils.loadAnimation(this,R.anim.nubes_splash);
        nubesLayout.setAnimation(nubesAnimacion);
        carroAnimacion = AnimationUtils.loadAnimation(this,R.anim.carro_splash);
        carroLayout.setAnimation(carroAnimacion);
        textoAnimacion = AnimationUtils.loadAnimation(this,R.anim.texto_splash);
        textoLayout.setAnimation(textoAnimacion);
    }
}
