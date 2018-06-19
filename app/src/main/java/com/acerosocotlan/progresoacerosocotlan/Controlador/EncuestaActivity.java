package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncuestaActivity extends AppCompatActivity {

    ImageView btn_feliz_encuesta_pregunta0,btn_contento_encuesta_pregunta0,btn_triste_encuesta_pregunta0,
              btn_feliz_encuesta_pregunta1,btn_contento_encuesta_pregunta1,btn_triste_encuesta_pregunta1,
              btn_feliz_encuesta_pregunta2,btn_contento_encuesta_pregunta2,btn_triste_encuesta_pregunta2,
              btn_feliz_encuesta_pregunta3,btn_contento_encuesta_pregunta3,btn_triste_encuesta_pregunta3,
              img_completado_encuesta_pregunta0,img_completado_encuesta_pregunta1,
              img_completado_encuesta_pregunta2,img_completado_encuesta_pregunta3;

    GridLayout gridlayout_encuesta;
    TextView txt_finalizacion_encuesta,txt_descripcion_encuesta;
    String vendedor, chofer, material, tiempo;
    private SharedPreferences prs;
    Vibrator v;
    int contadorPreguntas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        Inicializador();
        btn_feliz_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendedor="Bien";
                EfectoPregunta(1);
            }
        });

        btn_contento_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendedor = "Normal";
                EfectoPregunta(1);
            }
        });

        btn_triste_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendedor="Mal";
                EfectoPregunta(1);
            }
        });

        btn_feliz_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chofer="Bien";
                EfectoPregunta(2);
            }
        });

        btn_contento_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chofer="Normal";
                EfectoPregunta(2);
            }
        });

        btn_triste_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chofer="Mal";
                EfectoPregunta(2);
            }
        });

        btn_feliz_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                material="Bien";
                EfectoPregunta(3);
            }
        });

        btn_contento_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                material="Normal";
                EfectoPregunta(3);
            }
        });

        btn_triste_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                material="Mal";
                EfectoPregunta(3);
            }
        });

        btn_feliz_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempo="Bien";
                EfectoPregunta(4);
            }
        });

        btn_contento_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempo="Normal";
                EfectoPregunta(4);
            }
        });

        btn_triste_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempo="Mal";
                EfectoPregunta(4);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_agregar_comentario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EncuestaActivity.this, EncuestaComentario.class);
                startActivity(i);
            }
        });*/
    }
    private void EfectoPregunta(int pregunta) {
        switch (pregunta){
            case 1:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta0.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta0.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta0.setVisibility(View.GONE);
                img_completado_encuesta_pregunta0.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 2:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta1.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta1.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta1.setVisibility(View.GONE);
                img_completado_encuesta_pregunta1.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 3:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta2.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta2.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta2.setVisibility(View.GONE);
                img_completado_encuesta_pregunta2.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 4:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta3.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta3.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta3.setVisibility(View.GONE);
                img_completado_encuesta_pregunta3.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            default:
                break;
        }
    }
    private void ValidarContadorPreguntas() {
        if (contadorPreguntas==4){
            gridlayout_encuesta.setVisibility(View.INVISIBLE);
            txt_finalizacion_encuesta.setVisibility(View.VISIBLE);
            txt_descripcion_encuesta.setVisibility(View.INVISIBLE);
            EnviarEncuesta();
        }
    }
    private void EnviarEncuesta(){
        Call<List<String>> call = NetworkAdapter.getApiService().EnviarRespuestas(
                "guardarencuesta/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs), vendedor, chofer,material, tiempo);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.i("INSERTAR RESPUESTAS", "Insertando");
                if(response.isSuccessful()) {
                    List<String> respuesta = response.body();
                    //Log.i("RESPUESTAS", respuesta.toString());
                    if(respuesta.get(0).toString().equals("Guardado")){
                        try {
                            Thread.sleep(2000);
                            Intent intentProcesoEntrega = new Intent(EncuestaActivity.this, ProgresoEntregaActivity.class);
                            intentProcesoEntrega.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intentProcesoEntrega);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("ERROR ENCUESTA", t.getMessage());
                Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void Inicializador() {
        v= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);

        gridlayout_encuesta=(GridLayout) findViewById(R.id.gridlayout_encuesta);
        txt_finalizacion_encuesta=(TextView) findViewById(R.id.text_encuesta_finalizada);
        txt_descripcion_encuesta = (TextView) findViewById(R.id.txt_descripcion_encuesta);

        btn_feliz_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta0);
        btn_contento_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta0);
        btn_triste_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta0);

        btn_feliz_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta1);
        btn_contento_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta1);
        btn_triste_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta1);

        btn_feliz_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta2);
        btn_contento_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta2);
        btn_triste_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta2);

        btn_feliz_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta3);
        btn_contento_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta3);
        btn_triste_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta3);

        img_completado_encuesta_pregunta0=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta0);
        img_completado_encuesta_pregunta1=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta1);
        img_completado_encuesta_pregunta2=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta2);
        img_completado_encuesta_pregunta3=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta3);
    }
}
