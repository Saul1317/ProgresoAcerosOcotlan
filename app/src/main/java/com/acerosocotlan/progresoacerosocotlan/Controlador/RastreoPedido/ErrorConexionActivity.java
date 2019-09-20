package com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.ValidacionConexion;
import com.acerosocotlan.progresoacerosocotlan.R;

public class ErrorConexionActivity extends AppCompatActivity {
    private TextView txt_error_conexion;
    private ImageView error_conexion_simba_gracias,error_conexion_simba_cola, error_conexion_simba, error_conexion_corazon_simba,error_conexion_corazon_amarillo_simba;
    private Animation corazonAnimacion;
    private int contador = 0;
    private SharedPreferences prs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_conexion);
        IniciadorView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_recargar_app);
        /*
        * ESTE BOTON SIRVE PARA REINTENTAR DE NUEVO ESTABLECER CONEXION CON EL SERVICIO
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AL DAR CLICK SE VALIDA QUE QUE EL DISPOSITIVO TENGA CONEXION YA SEA WIFI O POR DATOS MOVILES
                if(ValidacionConexion.isConnectedWifi(getApplicationContext())||ValidacionConexion.isConnectedMobile(getApplicationContext())){
                    //SE VALIDA QUE SI SEA POSIBLE NAVEGAR
                    if(ValidacionConexion.isOnline(getApplicationContext())){
                        //SI SE CUMPLE LA CONDICION ENTONCES SE ABRIRA DE NUEVO LA VENTANA DE PRGRESO ENTREGA
                        Intent i = new Intent(ErrorConexionActivity.this, ProgresoEntregaActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }else{
                        Toast.makeText(ErrorConexionActivity.this, "No tienes acceso a internet", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ErrorConexionActivity.this, "Esta apagado tu WIFI", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*ANIMACION PERRO*/
        error_conexion_simba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador<5) {
                    error_conexion_corazon_simba.setVisibility(View.VISIBLE);
                    corazonAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.corazonsimba_animation);
                    error_conexion_corazon_simba.setAnimation(corazonAnimacion);
                    error_conexion_corazon_simba.setVisibility(View.INVISIBLE);

                    error_conexion_corazon_amarillo_simba.setVisibility(View.VISIBLE);
                    corazonAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.corazonsimba_animation);
                    error_conexion_corazon_amarillo_simba.setAnimation(corazonAnimacion);
                    error_conexion_corazon_amarillo_simba.setVisibility(View.INVISIBLE);

                    corazonAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cola_simaba_animation);
                    error_conexion_simba_cola.setAnimation(corazonAnimacion);

                    contador = contador + 1;
                }else{

                    error_conexion_simba_gracias.setVisibility(View.VISIBLE);
                    corazonAnimacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cola_simaba_animation);
                    error_conexion_simba_cola.setAnimation(corazonAnimacion);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            contador=0;
                            error_conexion_simba_gracias.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                }
            }
        });
    }

    /*INICIALIZA TODOS LOS VIEWS DE LA VISTA*/
    private void IniciadorView() {
        txt_error_conexion= (TextView) findViewById(R.id.txt_error_conexion);
        error_conexion_corazon_simba = (ImageView) findViewById(R.id.error_conexion_corazon_simba);
        error_conexion_simba = (ImageView) findViewById(R.id.error_conexion_simba);
        error_conexion_simba_cola = (ImageView) findViewById(R.id.error_conexion_simba_cola);
        error_conexion_corazon_amarillo_simba = (ImageView) findViewById(R.id.error_conexion_corazon_amarillo_simba);
        error_conexion_simba_gracias  = (ImageView) findViewById(R.id.error_conexion_simba_gracias);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
    }
}
