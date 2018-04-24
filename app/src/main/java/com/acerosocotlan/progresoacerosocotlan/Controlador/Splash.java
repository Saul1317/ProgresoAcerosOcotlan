package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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

public class Splash extends AppCompatActivity {

    Animation nubesAnimacion, carroAnimacion, textoAnimacion, cardviewAnimacion;
    LinearLayout textoLayout, cardview_folio_entrega;
    FrameLayout nubesLayout, carroLayout;
    Button boton_enviar_folio;
    TextView text_id_entrega;
    //SHARED PREFERENCE
    private SharedPreferences prs;

    //http://entregas.dyndns.org/web/entregas/statusentrega_ib958q/gao

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Inicializador();
        boton_enviar_folio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String codigo=text_id_entrega.getText().toString();
                if (!codigo.isEmpty()) {
                    Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                            "statusentrega_"+codigo+"/gao");
                    call.enqueue(new Callback<List<StatuEntrega>>() {
                        @Override
                        public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                            if(response.isSuccessful()) {
                                List<StatuEntrega> respuesta = response.body();
                                if(respuesta.isEmpty()){
                                    Toast.makeText(Splash.this, "No existe el codigo", Toast.LENGTH_SHORT).show();
                                }else{
                                    Log.i("CONSULTA","Correcto");
                                    MetodosSharedPreference.GuardarCodigoEntrega(prs, codigo);
                                    Intent i = new Intent(Splash.this, ProgresoEntregaActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                            Log.i("CONSULTA","Error al conectar");
                        }
                    });
                }else{
                    Toast.makeText(Splash.this, "Ingrese el id de su entrega", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Inicializador(){
        nubesLayout= (FrameLayout) findViewById(R.id.nubes_splash);
        carroLayout= (FrameLayout) findViewById(R.id.carro_splash);
        textoLayout= (LinearLayout) findViewById(R.id.texto_splash);
        text_id_entrega = (TextView) findViewById(R.id.text_id_entrega);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);

        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        cardview_folio_entrega= (LinearLayout) findViewById(R.id.cardview_edittext);
        nubesAnimacion = AnimationUtils.loadAnimation(this,R.anim.nubes_splash);
        nubesLayout.setAnimation(nubesAnimacion);
        carroAnimacion = AnimationUtils.loadAnimation(this,R.anim.carro_splash);
        carroLayout.setAnimation(carroAnimacion);
        textoAnimacion = AnimationUtils.loadAnimation(this,R.anim.texto_splash);
        textoLayout.setAnimation(textoAnimacion);
        cardviewAnimacion= AnimationUtils.loadAnimation(this,R.anim.cardview_splash);
        cardview_folio_entrega.setAnimation(cardviewAnimacion);
    }
}
