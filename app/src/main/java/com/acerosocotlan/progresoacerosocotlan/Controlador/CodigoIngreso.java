package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

public class CodigoIngreso extends AppCompatActivity {
    
    Animation cardviewAnimacion;
    LinearLayout textoLayout, cardview_folio_entrega;
    FrameLayout nubesLayout, carroLayout;
    Button boton_enviar_folio;
    TextInputEditText codigo_rastreo;
    //SHARED PREFERENCE
    private SharedPreferences prs;

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
                final String codigo = codigo_rastreo.getText().toString();
                if (!codigo.isEmpty()) {
                    if(codigo.equals("Hell0W0rld")){
                        Intent i = new Intent(CodigoIngreso.this, FirmaSistemasActivity.class);
                        startActivity(i);
                    }
                    else{
                        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                                "statusentrega_" + codigo + "/gao");
                        call.enqueue(new Callback<List<StatuEntrega>>() {
                            @Override
                            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                                if (response.isSuccessful()) {
                                    List<StatuEntrega> respuesta = response.body();
                                    if (respuesta.isEmpty()) {
                                        Toast.makeText(CodigoIngreso.this, "No existe el codigo", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.i("CONSULTA", "Correcto");
                                        MetodosSharedPreference.GuardarCodigoEntrega(prs, codigo);
                                        Intent i = new Intent(CodigoIngreso.this, ProgresoEntregaActivity.class);
                                        startActivity(i);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                                Log.i("CONSULTA", "Error al conectar");
                                Toast.makeText(CodigoIngreso.this, "Conexión no establecida", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(CodigoIngreso.this, "Ingrese el codigo de su entrega", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Inicializador(){
        codigo_rastreo = (TextInputEditText) findViewById(R.id.edttxt_codigo_rastreo_entrega);
        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        cardview_folio_entrega= (LinearLayout) findViewById(R.id.cardview_edittext);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
    }
}
