package com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerViewOfertas;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DirectorioTelefonos;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;

public class VerOferta extends AppCompatActivity {

    private Animation deslizamientoManoAnimacion,touchAnimation;
    private static final long VIBRACION_TIEMPO = 50;
    private RecyclerView ofertasRecyclerView;
    private ImageView imagen_fondo_estatus, deslizamiento_tuto, imagen_touch_mano_ver_ofertas_azul,imagen_touch_mano_ver_ofertas_verde;
    private String status, sociedad;
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;
    private Vibrator vibrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_oferta);
        Inicializador();
        ObtenerOfertas();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_telefono_llamada);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarDialogCustomLLamada();
            }
        });
    }

    private void Inicializador(){
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        progressDoalog = new ProgressDialog(VerOferta.this);
        ofertasRecyclerView = (RecyclerView) findViewById(R.id.ofertas_aceros_ocotlan_recyclerview);
        imagen_fondo_estatus=(ImageView) findViewById(R.id.imagen_fondo_estatus);
        deslizamiento_tuto=(ImageView) findViewById(R.id.deslizamiento_tuto);
        imagen_touch_mano_ver_ofertas_azul=(ImageView) findViewById(R.id.imagen_touch_mano_ver_ofertas_azul);
        imagen_touch_mano_ver_ofertas_verde=(ImageView) findViewById(R.id.imagen_touch_mano_ver_ofertas_verde);
        touchAnimation  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick2);
        vibrador = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Obteniendo los datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        RecogerEstatusEntrega();
    }

    private void RecogerEstatusEntrega(){
        progressDoalog.show();
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                if (response.isSuccessful()) {
                    List<StatuEntrega> respuesta = response.body();
                    ValidarEstatusActualEntrega(respuesta);
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                Intent intentErrorConexion = new Intent(VerOferta.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }

    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        status = respuesta.get(0).getEstatus();
        sociedad = respuesta.get(0).getSociedad();

        if(status.equals("Programado")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_verde.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("En Ruta")){
            if(respuesta.get(0).getSociedad().equals("ZULA")){
                imagen_fondo_estatus.setImageResource(R.drawable.progreso_zula_ya_vamos);
            }else{
                imagen_fondo_estatus.setImageResource(R.drawable.proceso1);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_verde.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("Proximo")){
            if(respuesta.get(0).getSociedad().equals("ZULA")){
                imagen_fondo_estatus.setImageResource(R.drawable.progreso_zula_ya_vamos);
            }else{
                imagen_fondo_estatus.setImageResource(R.drawable.proceso2);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_verde.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("En sitio")){
            if(respuesta.get(0).getSociedad().equals("ZULA")){
                imagen_fondo_estatus.setImageResource(R.drawable.progreso_zula_en_sitio);
            }else{
                imagen_fondo_estatus.setImageResource(R.drawable.proceso3);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_verde.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("Descargando")){
            if(respuesta.get(0).getSociedad().equals("ZULA")){
                imagen_fondo_estatus.setImageResource(R.drawable.progreso_zula_descargando);
            }else{
                imagen_fondo_estatus.setImageResource(R.drawable.proceso4);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_verde.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_verde.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("Entregado")){
            if(respuesta.get(0).getSociedad().equals("ZULA")){
                imagen_fondo_estatus.setImageResource(R.drawable.progreso_zula_listo);
            }else{
                imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso5);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_azul.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_azul.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_azul.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
        else if(status.equals("Posponer")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imagen_touch_mano_ver_ofertas_azul.setVisibility(View.VISIBLE);
                    imagen_touch_mano_ver_ofertas_azul.setAnimation(touchAnimation);
                    imagen_touch_mano_ver_ofertas_azul.setVisibility(View.INVISIBLE);
                }
            },3000);
        }
    }

    /*
    * CONSULTA PARA SACAR LAS OFERTAS DISPONIBLES EN LA SUCURSAL
    */
    private void ObtenerOfertas(){
        Call<List<VerOfertas_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).VerOfEntrega(
                "verpromo/gao",
                MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<List<VerOfertas_retrofit>>() {
            @Override
            public void onResponse(Call<List<VerOfertas_retrofit>> call, Response<List<VerOfertas_retrofit>> response) {
                progressDoalog.dismiss();
                if (response.isSuccessful()){
                    //SE ALMACENAN LAS OFERTAS EN UNA LISTA DE TIPO VEROFERTAS
                    List<VerOfertas_retrofit> verOfertas_retrofits = response.body();
                    //SE VALIDA QUE NO ESTE VACIA LA LISTA
                    if(verOfertas_retrofits.isEmpty()){
                        //MANDA UN DIALOG EN CASO DE QUE ESTE VACIA
                        MostrarDialogCustomSinOfertas();
                    }else {
                        //SI CONTIENE DATOS LA LISTA ENTONCES MANDAMOS LLAMAR ESTE METODO PARA LLENARA EL RECYCLER VIEW
                        LlenarRecyclerView(verOfertas_retrofits);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<VerOfertas_retrofit>> call, Throwable t) {
                progressDoalog.dismiss();
                Intent intentErrorConexion = new Intent(VerOferta.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }

    /*
    * SE LLENA EL RECYCLER VIEW PARA QUE SE MUESTREN LAS OFERTAS DISPONIBLES
    */
    private void LlenarRecyclerView(List<VerOfertas_retrofit> OfertasList){
        LinearLayoutManager l  = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ofertasRecyclerView.setLayoutManager(l);
        AdapterRecyclerViewOfertas arv = new AdapterRecyclerViewOfertas(OfertasList,R.layout.cardview_ofertas, VerOferta.this, getApplicationContext());
        ofertasRecyclerView.setAdapter(arv);
        //ANIMACION DE LA MANO DESLIZANDOSE
        deslizamientoManoAnimacion = AnimationUtils.loadAnimation(this,R.anim.deslizamientodedo);
        deslizamiento_tuto.setAnimation(deslizamientoManoAnimacion);
        deslizamiento_tuto.setVisibility(View.INVISIBLE);
    }

    /*
    * DIALOG DE CONFIRMACION PARA REALIZAR UNA LLAMADA
    */
    private void MostrarDialogCustomLLamada(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_acerosocotlan, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_si_ver_ofertas);
        Button botonCancelar = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_ver_ofertas);
        TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_titulo);
        TextView txtDescripcion  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_descripcion);

        txtTitutlo.setText(getResources().getString(R.string.dialog_txt_titulo_Llamada_correo));
        txtDescripcion.setText(getResources().getString(R.string.dialog_txt_descripcion_Llamada));
        botonEntendido.setText(getResources().getString(R.string.txt_si));
        botonCancelar.setText(getResources().getString(R.string.txt_no));

        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                SolicitarTelefono();
                alertDialog.dismiss();
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                alertDialog.dismiss();
            }
        });
    }

    //EN CASO DE QUE NO SE ENCUENTREN OFERTAS APARECERA ESTE MENSAJE
    private void MostrarDialogCustomSinOfertas(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        alert.setCancelable(false);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();

        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_conexion_titulo);
        TextView txtDescripcion  = (TextView) dialoglayout.findViewById(R.id.dialog_conexion_descripcion);

        txtTitutlo.setText(getResources().getString(R.string.dialog_txt_titulo_sin_ofertas));
        txtDescripcion.setText(getResources().getString(R.string.dialog_txt_descripcion_sin_ofertas));
        botonEntendido.setText(getResources().getString(R.string.txt_entendido));

        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intentProgreso = new Intent(VerOferta.this, ProgresoEntregaActivity.class);
                intentProgreso.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentProgreso);
            }
        });
    }

    /*
    * HACE UNA PETICION AL WEB SERVICE PARA SABER CUAL ES TELEFONO DE LA SUCURSAR EN BASE AL CODIGO DE RASTREO
    */
    private void SolicitarTelefono(){
        Call<DirectorioTelefonos> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).SolicitarTelefono(
                "directorio/gao",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<DirectorioTelefonos>() {
            @Override
            public void onResponse(Call<DirectorioTelefonos> call, Response<DirectorioTelefonos> response) {
                //SE VALIDA LA RESPUESTA
                if(response.isSuccessful()) {
                    //SE RECOGE LA RESPUESTA Y SE ALMACENA EN UN OBJETO
                    DirectorioTelefonos directorioTelefonos = response.body();
                    //SE MANDA POR PARAMETRO EL TELEFONO PARA HACER LA LLAMADA
                    RealizarLLamada(directorioTelefonos.getTelefono());
                    Log.i("DIRECTORIO DE TELEFONOS", directorioTelefonos.getTelefono());
                }
            }

            @Override
            public void onFailure(Call<DirectorioTelefonos> call, Throwable t) {
                Log.i("DIRECTORIO DE TELEFONOS", "EROR");
            }
        });
    }

    /*
    * METODO PARA REALIZAR LA LLAMADA
    */
    private void RealizarLLamada(String phoneNumber) {
        //SE VALIDA LA VERSION DE ANDROID
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            //SE VALIDA QUE SE TENGAN LOS PERMISOS PARA HACER LLAMADAS
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //SI NO HAY PERMISOS ENTONCES SE SOLICITAN AL USUARIO
                ActivityCompat.requestPermissions(VerOferta.this, new String[]{CALL_PHONE},100);
            } else {
                //SI YA TIENE LOS PERMISOS ENTONCES SE REALIZA LA LLAMADA
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

}
