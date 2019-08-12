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
    private String vendedor, chofer, material, tiempo, aplicacion,  sociedad;
    private SharedPreferences prs;
    private Vibrator v;
    //VARIABLE PARA DETERMINAR CUANTAS PREGUNTAS SE HAN CONTESTADO
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
        //AL INICIAL LA VISTA SE HARA UNA PETICION AL SERVIDOR PARA OBTENER LOS DATOS DEL PEDIDO
        RecogerEstatusEntrega();
    }
    /*
    * PETICION PARA OBTENER LA INFORMCION DEL PEDIDO DEL CLIENTE
    * SE MANDA DENTRO LA URL EL CODIGO DE RASTREO
    */
    private void RecogerEstatusEntrega(){
        //METODO RETROFIT QUE MANDA POR LA URL EL CODIGO DE RASTRE Y QUE RECIBE UNA LISTA DE OBJETOS DE TIPO STATUS ENTREGA
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if (response.isSuccessful()) {
                    //SI LA RESPUESTA ES CORRECTA ENTONCES SE ALMACENA EN UNA LISTA DE TIPO STATU ENTREGA
                    List<StatuEntrega> respuesta = response.body();
                    //status = respuesta.get(0).getEstatus();
                    // MetodosSharedPreference.GuardarEstatusEntrega(prs, status);

                    //SE MANDA LLAMAR ESTE METODO PARA VALIDAR EL ESTATUS DEL PEDIDO Y ASI CONFIGURAR LAS IMAGENES, SE MANDA LA RESPUESTA POR PARAMETRO
                    ValidarEstatusActualEntrega(respuesta);
                }else{
                    //SI LA RESPUESTA ES ERRONEA ENTONCES SE ABRIRA UNA VENTANA DE ERROR DE CONEXION
                    Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                //SI NO SE ESTABLECE LA CONEXION AL SERVIDOR ENTONCES SE ABRIRA UNA VENTANA DE ERROR DE CONEXION
                Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }

    /*
    * METODO PARA VALIDAR EL ESTATUS DEL PEDIDO Y PODER ASIGNAR LA IMAGEN DE FONDO
    *
    * NOTA: SE HABIA HABLA DE QUE SE IMPLEMENTARIA UNA VALIDACION EN DONDE SI LA SOCIEDAD ERA ZULA
    * ENTONCES SE USARIA UNA BARRA DE PROGRESO DIFERENTE CON SOLO 4 PROCESOS EN LUGAR DE 5 POR CUESTIONES DE SEGURIDAD
    * PERO A DIA DE HOY 29-JULIO-2019 NO SE A DICHO NADA PERO LA VALIDACION Y LA BARRA DE 4 ESTATUS YA ESTAN IMPLEMENTADOS
    *
    */
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        //SEGUN EL ESTATUS EL LA IMAGEN QUE SE USARA EN LA APLICACION
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

    /*
    * METODO PARA OCULTAR LOS EMOJIS AL MOMENTO DE HACER CLICK EN ELLOS
    * SE VALIDA EL NUMERO DE PREGUNTA Y DESPUES SE OCULTAN TODOS LOS EMOJIS RELACIONADOS CON ESA PREGUNTA
    * PREGUNTA
    * 1.- VENDEDOR
    * 2.- CHOFER
    * 3.- MATERIAL
    * 4.- TIEMPO DE ENTREGA
    * 5.- CALIFICACION DE LA APLICACION
    */
    private void EfectoPregunta(int pregunta) {
        switch (pregunta){
            case 1:
                v.vibrate(200);
                //SE SUMA UNA PREGUNTA CONTESTADA
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta0.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta0.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta0.setVisibility(View.GONE);
                img_completado_encuesta_pregunta0.setVisibility(View.VISIBLE);
                //SE MANDA A LLAMAR EL METODO PARA VALIDAR EL NUMERO DE PREGUNTAS CONTESTADAS
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

    /*
    * VERIFICA SI YA SE CONTESTARON TODAS LAS PREGUNTAS PARA DESPUES MANDAR LOS RESULTADOS Y SALIR DE LA ENCUESTA
    */
    private void ValidarContadorPreguntas() {
        //SE VALIDA SI LAS 5 PREGUNTAS YA FUERON CONTESTADAS
        if (contadorPreguntas==5){
            //SI CUMPLE LA CONDICION ENTONCES SE OCULTA LA INFORMACION Y APARECE UN MENSAJE DE GRACIAS
            gridlayout_encuesta.setVisibility(View.INVISIBLE);
            txt_finalizacion_encuesta.setVisibility(View.VISIBLE);
            txt_descripcion_encuesta.setVisibility(View.INVISIBLE);
            //SE MANDA LA ENCUESTA
            EnviarEncuesta();
        }
    }

    /*
    * SE MANDAN LAS RESPUESTAS DE LA ENCUESTA EN UNA PETICION POST
    */
    private void EnviarEncuesta(){
        Call<List<String>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EnviarRespuestas(
                "guardarencuesta/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs), vendedor, chofer, material, tiempo, aplicacion, "ANDROID");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.i("INSERTAR RESPUESTAS", "Insertando");
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if(response.isSuccessful()) {
                    //SE ALMACENA LA RESPUESTA DEL SERVIDOR
                    List<String> respuesta = response.body();
                    //SE VALIDA EL MENSAJE DE LA RESPUESTA
                    if(respuesta.get(0).toString().equals("Guardado")){
                        try {
                            //ESPERAMOS 2 SEGUNGOS PARA ABRIR LA VENTANA DE PROGRESO DE NUEVO
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
                    //SI HAY UN ERROR CON LA RESPUESTA DEL SERVIDOR ENTONCES SE ABRIRA LA VENTANA DE ERROR CONEXION
                    Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //SI HAY UN ERROR EN LA CONEXION CON EL SERVIDOR ENTONCES SE ABRIRA LA VENTANA DE ERROR CONEXION
                Log.i("ERROR ENCUESTA", t.getMessage());
                Intent intentErrorConexion = new Intent(EncuestaActivity.this, ErrorConexionActivity.class);
                startActivity(intentErrorConexion);
            }
        });
    }

}
