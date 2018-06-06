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
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerView;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.Modelo.ValidacionConexion;
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
    String status;
    private SharedPreferences prs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrega2);
        inicializador();
        ObtenerDetalleEntrega();
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
                Intent i = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
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
    public void RecogerEstatusEntrega(){
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                List<StatuEntrega> respuesta = response.body();
                status = respuesta.get(0).getEstatus();
                MetodosSharedPreference.GuardarEstatusEntrega(prs,status);
                ValidarEstatusActualEntrega(respuesta);
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                Intent intentErrorConexion = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        if(status.equals("Programado")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
        }
        else if(status.equals("En Ruta")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso1);
        }
        else if(status.equals("Proximo")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso2);
        }
        else if(status.equals("En sitio")){
            imageViewFondoDetallesEntrega.setBackgroundResource(R.drawable.proceso3);
        }
        else if(status.equals("Descargando")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso4);
        }
        else if(status.equals("Entregado")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso5);
        }
        else if(status.equals("Cancelado")){
            imageViewFondoDetallesEntrega.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
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
        RecogerEstatusEntrega();

        //Log.i("CODIGO",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        //Log.i("ESTATUS",MetodosSharedPreference.ObtenerEstatusEntregaPref(prs));
    }
}
