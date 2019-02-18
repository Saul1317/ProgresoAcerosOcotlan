package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncuestaActivity extends AppCompatActivity {

    private ImageView btn_feliz_encuesta_pregunta0,btn_contento_encuesta_pregunta0,btn_triste_encuesta_pregunta0,
              btn_feliz_encuesta_pregunta1,btn_contento_encuesta_pregunta1,btn_triste_encuesta_pregunta1,
              btn_feliz_encuesta_pregunta2,btn_contento_encuesta_pregunta2,btn_triste_encuesta_pregunta2,
              btn_feliz_encuesta_pregunta3,btn_contento_encuesta_pregunta3,btn_triste_encuesta_pregunta3,
              btn_feliz_encuesta_pregunta4,btn_contento_encuesta_pregunta4,btn_triste_encuesta_pregunta4,
              img_completado_encuesta_pregunta0,img_completado_encuesta_pregunta1,
              img_completado_encuesta_pregunta2,img_completado_encuesta_pregunta3,
              img_completado_encuesta_pregunta4, imagen_fondo_detalles_encuesta;

    private GridLayout gridlayout_encuesta;
    private TextView txt_finalizacion_encuesta,txt_descripcion_encuesta;
    private String vendedor, chofer, material, tiempo, aplicacion, status, sociedad;
    private SharedPreferences prs;
    private Vibrator v;
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

        btn_feliz_encuesta_pregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aplicacion="Bien";
                EfectoPregunta(5);
            }
        });

        btn_contento_encuesta_pregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aplicacion="Normal";
                EfectoPregunta(5);
            }
        });

        btn_triste_encuesta_pregunta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aplicacion="Mal";
                EfectoPregunta(5);
            }
        });
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
            case 5:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta4.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta4.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta4.setVisibility(View.GONE);
                img_completado_encuesta_pregunta4.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            default:
                break;
        }
    }
    private void ValidarContadorPreguntas() {
        if (contadorPreguntas==5){
            gridlayout_encuesta.setVisibility(View.INVISIBLE);
            txt_finalizacion_encuesta.setVisibility(View.VISIBLE);
            txt_descripcion_encuesta.setVisibility(View.INVISIBLE);
            EnviarEncuesta();
        }
    }
    private void EnviarEncuesta(){
        Call<List<String>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EnviarRespuestas(
                "guardarencuesta/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs), vendedor, chofer, material, tiempo, aplicacion, "ANDROID");
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
                    }else{
                        Log.e("ERROR GUARDADO", "ocurrio un error");
                    }
                }else{
                    Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
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

        imagen_fondo_detalles_encuesta = (ImageView) findViewById(R.id.imagen_fondo_detalles_encuesta);

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

        btn_feliz_encuesta_pregunta4=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta4);
        btn_contento_encuesta_pregunta4=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta4);
        btn_triste_encuesta_pregunta4=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta4);

        img_completado_encuesta_pregunta0=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta0);
        img_completado_encuesta_pregunta1=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta1);
        img_completado_encuesta_pregunta2=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta2);
        img_completado_encuesta_pregunta3=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta3);
        img_completado_encuesta_pregunta4=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta4);
        RecogerEstatusEntrega();
    }
    public void RecogerEstatusEntrega(){
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                if (response.isSuccessful()) {
                    List<StatuEntrega> respuesta = response.body();
                    status = respuesta.get(0).getEstatus();
                    MetodosSharedPreference.GuardarEstatusEntrega(prs, status);
                    ValidarEstatusActualEntrega(respuesta);
                }else{
                    Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        switch (respuesta.get(0).getEstatus()){
            case "Programado":
                imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
                break;

            case "En Ruta":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso1);
                }
                break;

            case "Proximo":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso2);
                }
                break;

            case "En sitio":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_en_sitio);
                }else{
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso3);
                }
                break;

            case "Descargando":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_descargando);
                }else{
                    imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso4);
                }
                break;

            case "Entregado":

                if (respuesta.get(0).getHizoencuesta().equals("0")){
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso5);
                    }
                }else {
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imagen_fondo_detalles_encuesta.setImageResource(R.drawable.proceso5);
                    }
                }
                break;

            case "Posponer":
                imagen_fondo_detalles_encuesta.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
                break;

            default:
                Toast.makeText(this, "Ocurrió un problema, intente de nuevo", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
