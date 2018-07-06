package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerView;
import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerViewOfertas;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
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

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;

public class VerOferta extends AppCompatActivity {

    private Animation deslizamientoManoAnimacion,touchAnimation;
    private RecyclerView ofertasRecyclerView;
    private ImageView imagen_fondo_estatus, deslizamiento_tuto, imagen_touch_mano_ver_ofertas_azul,imagen_touch_mano_ver_ofertas_verde;
    private String status;
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;

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
    private void RecogerEstatusEntrega(){
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Obteniendo los datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                if (response.isSuccessful()) {
                    List<StatuEntrega> respuesta = response.body();
                    ValidarEstatusActualEntrega(respuesta.get(0).getEstatus().toString());

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
    private void ValidarEstatusActualEntrega(String respuesta) {
        status = respuesta;
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
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso1);
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
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso2);
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
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso3);
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
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso4);
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
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso5);
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
    private void ObtenerOfertas(){
        Call<List<VerOfertas_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).VerOfEntrega("verpromo/gao");
        call.enqueue(new Callback<List<VerOfertas_retrofit>>() {
            @Override
            public void onResponse(Call<List<VerOfertas_retrofit>> call, Response<List<VerOfertas_retrofit>> response) {
                progressDoalog.dismiss();
                if (response.isSuccessful()){
                    List<VerOfertas_retrofit> verOfertas_retrofits = response.body();
                    if(verOfertas_retrofits.isEmpty()){
                        MostrarDialogCustomSinOfertas();
                    }else {
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
    private void LlenarRecyclerView(List<VerOfertas_retrofit> OfertasList){
        //LinearLayoutManager l  = new GridLayoutManager(getApplicationContext(),3);
        LinearLayoutManager l  = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ofertasRecyclerView.setLayoutManager(l);
        AdapterRecyclerViewOfertas arv = new AdapterRecyclerViewOfertas(OfertasList,R.layout.cardview_ofertas, VerOferta.this, getApplicationContext());
        ofertasRecyclerView.setAdapter(arv);
        deslizamientoManoAnimacion = AnimationUtils.loadAnimation(this,R.anim.deslizamientodedo);
        deslizamiento_tuto.setAnimation(deslizamientoManoAnimacion);
        deslizamiento_tuto.setVisibility(View.INVISIBLE);
    }
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
                SolicitarTelefono();
                alertDialog.dismiss();
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
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
    private void SolicitarTelefono(){
        Call<DirectorioTelefonos> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).SolicitarTelefono(
                "directorio/gao",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<DirectorioTelefonos>() {
            @Override
            public void onResponse(Call<DirectorioTelefonos> call, Response<DirectorioTelefonos> response) {
                if(response.isSuccessful()) {
                    DirectorioTelefonos directorioTelefonos = response.body();
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
    private void RealizarLLamada(String phoneNumber) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(VerOferta.this, new String[]{CALL_PHONE},100);
            } else {
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
    private void Inicializador(){
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        progressDoalog = new ProgressDialog(VerOferta.this);
        ofertasRecyclerView = (RecyclerView) findViewById(R.id.ofertas_aceros_ocotlan_recyclerview);
        imagen_fondo_estatus=(ImageView) findViewById(R.id.imagen_fondo_estatus);
        deslizamiento_tuto=(ImageView) findViewById(R.id.deslizamiento_tuto);
        imagen_touch_mano_ver_ofertas_azul=(ImageView) findViewById(R.id.imagen_touch_mano_ver_ofertas_azul);
        imagen_touch_mano_ver_ofertas_verde=(ImageView) findViewById(R.id.imagen_touch_mano_ver_ofertas_verde);
        touchAnimation  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick2);
        RecogerEstatusEntrega();
    }
}
