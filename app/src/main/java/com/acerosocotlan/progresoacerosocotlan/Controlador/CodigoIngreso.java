package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Prueba_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodigoIngreso extends AppCompatActivity {

    private Button boton_enviar_folio;
    private TextInputEditText codigo_rastreo;
    private Animation carro_animacion,humo_animacion, carro_salida_animacion;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;
    private String txtprueba1;
    private String txtprueba2;
    private String txtprueba3;
    private ImageView camion_click, camion_humo;
    private int firma = 0;

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
        camion_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarroAnimacion();
            }
        });
    }

    private void CarroAnimacion(){
        if (firma>=9) {
            firma=0;
            camion_click.startAnimation(carro_salida_animacion);
            carro_salida_animacion.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    AbrirFirma();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }else if(firma>=6 && firma <=9 ){
            camion_humo.setVisibility(View.VISIBLE);
            camion_click.startAnimation(carro_animacion);
            camion_humo.startAnimation(humo_animacion);
            camion_humo.setVisibility(View.INVISIBLE);
            firma = firma + 1;
        }else if (firma<=5){
            camion_click.startAnimation(carro_animacion);
            firma = firma + 1;
        }
    }
    private void AbrirFirma() {
        Intent i = new Intent(CodigoIngreso.this, FirmaSistemasActivity.class);
        startActivity(i);
    }
    private void MostrarDialogCustomNoConfiguracion(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        //alert.setCancelable(false);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarUsuario();
                alertDialog.dismiss();
            }
        });
    }
    private void MostrarDialogCustomNoConexionServidor(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        //alert.setCancelable(false);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptar();
                alertDialog.dismiss();
            }
        });
    }
    private void ValidarUsuario(){
        txtprueba3 = codigo_rastreo.getText().toString();
        if (!txtprueba3.isEmpty()) {
            if(txtprueba3.equals("Hell0W0rld")){

            }
            else{
               prueba();
            }
        } else {
            Toast.makeText(CodigoIngreso.this, "Ingrese el codigo de su entrega", Toast.LENGTH_SHORT).show();
        }
    }
    private void prueba(){
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Validando sus datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Call<Prueba_retrofit> call = NetworkAdapter.getApiServiceAlternativo().Solicitarprueba("egao.php",txtprueba1,txtprueba2);
        call.enqueue(new Callback<Prueba_retrofit>() {
            @Override
            public void onResponse(Call<Prueba_retrofit> call, Response<Prueba_retrofit> response) {
                progressDoalog.dismiss();
                if(response.isSuccessful()) {
                    Prueba_retrofit  prueba_retrofit= response.body();
                    if (!prueba_retrofit.getResp().isEmpty()) {
                        MetodosSharedPreference.GuardarPruebaEntrega(prs, prueba_retrofit.getResp());
                        Log.i("URL", MetodosSharedPreference.ObtenerPruebaEntregaPref(prs));
                        ConsultarCodigo();
                    }else{
                        MostrarDialogCustomNoConfiguracion();
                    }
                }else{
                    MostrarDialogCustomNoConfiguracion();
                }
            }

            @Override
            public void onFailure(Call<Prueba_retrofit> call, Throwable t) {
                //MostrarDialogCustomNoConfiguracion();
                Log.i("ERROR SERVER","Primer link fallo");
                prueba2();
                progressDoalog.dismiss();
            }
        });
    }
    private void prueba2(){
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Validando sus datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Call<Prueba_retrofit> call = NetworkAdapter.getApiServiceAlternativo2().Solicitarprueba("egao.php",txtprueba1,txtprueba2);
        call.enqueue(new Callback<Prueba_retrofit>() {
            @Override
            public void onResponse(Call<Prueba_retrofit> call, Response<Prueba_retrofit> response) {
                progressDoalog.dismiss();
                if(response.isSuccessful()) {
                    Prueba_retrofit  prueba_retrofit= response.body();
                    if (!prueba_retrofit.getResp().isEmpty()) {
                        MetodosSharedPreference.GuardarPruebaEntrega(prs, prueba_retrofit.getResp());
                        Log.i("URL", MetodosSharedPreference.ObtenerPruebaEntregaPref(prs));
                        ConsultarCodigo();
                    }else{
                        MostrarDialogCustomNoConexionServidor();
                    }
                }else{
                        MostrarDialogCustomNoConexionServidor();
                }
            }

            @Override
            public void onFailure(Call<Prueba_retrofit> call, Throwable t) {
                progressDoalog.dismiss();
                Log.i("ERROR SERVER","Segundo link fallo");
                MostrarDialogCustomNoConexionServidor();
            }
        });
    }
    private void ConsultarCodigo(){
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_" + txtprueba3 + "/gao");
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
                        MetodosSharedPreference.GuardarCodigoEntrega(prs, txtprueba3);
                        Intent i = new Intent(CodigoIngreso.this, ProgresoEntregaActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }else{
                    MostrarDialogCustomNoConfiguracion();
                }
            }
            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                MostrarDialogCustomNoConfiguracion();
            }
        });
    }
    private void encryptar(){
        String text1 = "codigo";
        String text2 = "binarioxd";
        byte[] encrpt1;
        byte[] encrpt2;
        
        try {
            encrpt1 = text1.getBytes("UTF-8");
            encrpt2 = text2.getBytes("UTF-8");
            txtprueba1 = Base64.encodeToString(encrpt1, Base64.DEFAULT);
            txtprueba2 = Base64.encodeToString(encrpt2, Base64.DEFAULT);
            Log.i("USER", txtprueba1);
            Log.i("PASS", txtprueba2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private void Inicializador(){
        codigo_rastreo = (TextInputEditText) findViewById(R.id.edttxt_codigo_rastreo_entrega);
        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        progressDoalog = new ProgressDialog(CodigoIngreso.this);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        camion_click = (ImageView) findViewById(R.id.camion_click);
        camion_humo = (ImageView) findViewById(R.id.camion_humo);
        carro_animacion = AnimationUtils.loadAnimation(this,R.anim.camion_animacion);
        humo_animacion  = AnimationUtils.loadAnimation(this,R.anim.humo_animacion);
        carro_salida_animacion = AnimationUtils.loadAnimation(this,R.anim.camion_salida_animacion);
        encryptar();
    }
}
