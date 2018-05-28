package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerView;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleEntregaActivity2 extends AppCompatActivity {
    private RecyclerView detallesRecyclerview;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageViewFondoDetallesEntrega;
    private TextView textViewEstatus_detalle;
    private LinearLayout linear_layout_filtro_detalle;
    private SharedPreferences prs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrega2);
        inicializador();
        ValidarEstatusActualEntrega();
        ObtenerDetalleEntrega();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_nuevo_codigo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void ObtenerDetalleEntrega(){
        Call<List<DetalleEntrega_retrofit>> call = NetworkAdapter.getApiService().detalleEntrega("detalle_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<DetalleEntrega_retrofit>>() {
            @Override
            public void onResponse(Call<List<DetalleEntrega_retrofit>> call, Response<List<DetalleEntrega_retrofit>> response) {
                if (response.isSuccessful()){
                    List<DetalleEntrega_retrofit> detalleEntrega_retrofits = response.body();
                    LlenarRecyclerView(detalleEntrega_retrofits);
                }
            }

            @Override
            public void onFailure(Call<List<DetalleEntrega_retrofit>> call, Throwable t) {
            }
        });
    }
    private void LlenarRecyclerView(List<DetalleEntrega_retrofit> detalleEntrega_retrofits){
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        detallesRecyclerview.setLayoutManager(l);
        AdapterRecyclerView arv = new AdapterRecyclerView(detalleEntrega_retrofits,R.layout.cardview_detalle_entrega, DetalleEntregaActivity2.this, getApplicationContext());
        detallesRecyclerview.setAdapter(arv);
    }
    private void ValidarEstatusActualEntrega() {
        String status = MetodosSharedPreference.ObtenerEstatusEntregaPref(prs);
        if(status.equals("Programado")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
            linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
            textViewEstatus_detalle.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("En Ruta")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.proceso1);
            linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
            textViewEstatus_detalle.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("Proximo")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.proceso2);
            linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
            textViewEstatus_detalle.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("Descargando")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.proceso4);
            linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
            textViewEstatus_detalle.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("Entregado")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.proceso5);
            linear_layout_filtro_detalle.setVisibility(View.VISIBLE);
            textViewEstatus_detalle.setVisibility(View.VISIBLE);
        }
        else if(status.equals("Cancelado")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
            linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
            textViewEstatus_detalle.setVisibility(View.INVISIBLE);
        }
    }
    private void inicializador(){
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        detallesRecyclerview = (RecyclerView) findViewById(R.id.detalles_entregas_recyclerview);
        textViewEstatus_detalle = (TextView) findViewById(R.id.estatus_detalle);
        linear_layout_filtro_detalle = (LinearLayout) findViewById(R.id.linear_layout_filtro_detalle);
        imageViewFondoDetallesEntrega = (ImageView) findViewById(R.id.imagen_fondo_detalles_estatus);
        //collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
        textViewEstatus_detalle.setVisibility(View.INVISIBLE);

        //Log.i("CODIGO",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        //Log.i("ESTATUS",MetodosSharedPreference.ObtenerEstatusEntregaPref(prs));
    }
}
