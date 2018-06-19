package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.Manifest;
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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerView;
import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerViewOfertas;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
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

    Animation deslizamientoManoAnimacion,touchAnimation;
    RecyclerView ofertasRecyclerView;
    ImageView imagen_fondo_estatus, deslizamiento_tuto, imagen_touch_mano_ver_ofertas;
    String status;
    LinearLayout linear_layout_filtro_ofertas;
    private SharedPreferences prs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_oferta);
        Inicializador();
        ObtenerOfertas();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imagen_touch_mano_ver_ofertas.setVisibility(View.VISIBLE);
                imagen_touch_mano_ver_ofertas.setAnimation(touchAnimation);
                imagen_touch_mano_ver_ofertas.setVisibility(View.INVISIBLE);
            }
        },2000);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_telefono_llamada);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoConfirmacionLlamada();
            }
        });
    }
    public void RecogerEstatusEntrega(){
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                List<StatuEntrega> respuesta = response.body();
                ValidarEstatusActualEntrega(respuesta.get(0).getEstatus().toString());
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {

            }
        });
    }
    private void ValidarEstatusActualEntrega(String respuesta) {
        status = respuesta;
        if(status.equals("Programado")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
        }
        else if(status.equals("En Ruta")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso1);
        }
        else if(status.equals("Proximo")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso2);
        }
        else if(status.equals("En sitio")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso3);
        }
        else if(status.equals("Descargando")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso4);
        }
        else if(status.equals("Entregado")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.proceso5);
        }
        else if(status.equals("Cancelado")){
            imagen_fondo_estatus.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
        }
        deslizamientoManoAnimacion = AnimationUtils.loadAnimation(this,R.anim.deslizamientodedo);
        deslizamiento_tuto.setAnimation(deslizamientoManoAnimacion);
        deslizamiento_tuto.setVisibility(View.INVISIBLE);
    }
    private void ObtenerOfertas(){
        Call<List<VerOfertas_retrofit>> call = NetworkAdapter.getApiService().VerOfEntrega("verpromo/gao");
        call.enqueue(new Callback<List<VerOfertas_retrofit>>() {
            @Override
            public void onResponse(Call<List<VerOfertas_retrofit>> call, Response<List<VerOfertas_retrofit>> response) {
                if (response.isSuccessful()){
                    List<VerOfertas_retrofit> verOfertas_retrofits = response.body();
                    LlenarRecyclerView(verOfertas_retrofits);
                }
            }

            @Override
            public void onFailure(Call<List<VerOfertas_retrofit>> call, Throwable t) {
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
    }
    public void DialogoConfirmacionLlamada(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Desea comunicarse con nosotros para más información?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                RealizarLLamada("7777777777");
            }
        });
        alert.show();
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
        ofertasRecyclerView = (RecyclerView) findViewById(R.id.ofertas_aceros_ocotlan_recyclerview);
        imagen_fondo_estatus=(ImageView) findViewById(R.id.imagen_fondo_estatus);
        deslizamiento_tuto=(ImageView) findViewById(R.id.deslizamiento_tuto);
        linear_layout_filtro_ofertas= (LinearLayout) findViewById(R.id.linear_layout_filtro_ofertas);
        imagen_touch_mano_ver_ofertas=(ImageView) findViewById(R.id.imagen_touch_mano_ver_ofertas);
        touchAnimation  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick2);
        RecogerEstatusEntrega();
    }
}
