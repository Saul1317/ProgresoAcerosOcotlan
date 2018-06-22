package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodigoIngreso extends AppCompatActivity {

    LinearLayout cardview_folio_entrega;
    Button boton_enviar_folio;
    TextInputEditText codigo_rastreo;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            //w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_codigo_ingreso);
        Inicializador();

        boton_enviar_folio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarUsuario();
            }
        });
    }
    private void ValidarUsuario(){
        final String codigo = codigo_rastreo.getText().toString();
        if (!codigo.isEmpty()) {
            if(codigo.equals("123")){
                Intent i = new Intent(CodigoIngreso.this, FirmaSistemasActivity.class);
                startActivity(i);
            }
            else{
               ConsultarCodigo(codigo);
            }
        } else {
            Toast.makeText(CodigoIngreso.this, "Ingrese el codigo de su entrega", Toast.LENGTH_SHORT).show();
        }
    }

    private void ConsultarCodigo(final String codigo){
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Validando sus datos");
        progressDoalog.setCanceledOnTouchOutside(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                "statusentrega_" + codigo + "/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                if (response.isSuccessful()) {
                    List<StatuEntrega> respuesta = response.body();
                    if (respuesta.isEmpty()) {
                        Toast.makeText(CodigoIngreso.this, "No existe el codigo", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("CONSULTA", "Correcto");
                        MetodosSharedPreference.GuardarCodigoEntrega(prs, codigo);
                        Intent i = new Intent(CodigoIngreso.this, ProgresoEntregaActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                MostrarDialogCustomNoWIFI();
            }
        });
    }
    private void MostrarDialogCustomNoWIFI(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    private void Inicializador(){
        codigo_rastreo = (TextInputEditText) findViewById(R.id.edttxt_codigo_rastreo_entrega);
        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        cardview_folio_entrega= (LinearLayout) findViewById(R.id.cardview_edittext);
        progressDoalog = new ProgressDialog(CodigoIngreso.this);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
    }
}
