package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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

public class ProgresoEntregaActivity extends AppCompatActivity {

    ImageView imagen_progress_bar;
    TextView fecha_entregado;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    String codigo_entrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso_entrega);
        Inicializador();
        RecogerEstatusEntrega();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecogerEstatusEntrega();
            }
        });
    }

    public void Inicializador(){
        imagen_progress_bar = (ImageView) findViewById(R.id.imagen_progress_bar_estatus);
        fecha_entregado= (TextView) findViewById(R.id.fecha_llegada);
        fecha_entregado.setVisibility(View.INVISIBLE);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        codigo_entrega = MetodosSharedPreference.ObtenerCodigoEntregaPref(prs);
    }
    public void RecogerEstatusEntrega(){
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                "statusentrega_"+codigo_entrega+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                List<StatuEntrega> respuesta = response.body();
                ValidarEstatusActualEntrega(respuesta);
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {

            }
        });
    }
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        String status = respuesta.get(0).getEstatus();

        if(status.equals("Programado")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
        }
        else if(status.equals("En Ruta")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_6_1);
        }
        else if(status.equals("Proximo")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_6_2);
        }
        else if(status.equals("Descargando")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_completado);

        }
        else if(status.equals("Entregado")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_completado);
            fecha_entregado.setVisibility(View.VISIBLE);
            fecha_entregado.setText(respuesta.get(0).getFLlegadaEntrega().toString());
        }
        else if(status.equals("Cancelada")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
        }
    }
    private void ValidarEstatusActualEntregaPorFechas(List<StatuEntrega> respuesta) {
        //respuesta.get(0).getEstatus();

        if(respuesta.get(0).getFInicioRuta().isEmpty() && respuesta.get(0).getFInicioRuta().isEmpty()
                && respuesta.get(0).getFInicioEntrega().isEmpty() && respuesta.get(0).getFLlegadaEntrega().isEmpty()){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
        }
        else if(!respuesta.get(0).getFInicioRuta().isEmpty()&& respuesta.get(0).getFInicioRuta().isEmpty()
                && respuesta.get(0).getFInicioEntrega().isEmpty() && respuesta.get(0).getFLlegadaEntrega().isEmpty()){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_6_1);
        }
        else if(!respuesta.get(0).getFInicioRuta().isEmpty()&&!respuesta.get(0).getFInicioEntrega().isEmpty()
                && respuesta.get(0).getFLlegadaEntrega().isEmpty()){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_6_2);
        }
        else if(!respuesta.get(0).getFInicioRuta().isEmpty()&&!respuesta.get(0).getFInicioEntrega().isEmpty()
                && !respuesta.get(0).getFLlegadaEntrega().isEmpty()&&!respuesta.get(0).getFLlegadaEntrega().isEmpty()){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_completado);
        }
    }

}
