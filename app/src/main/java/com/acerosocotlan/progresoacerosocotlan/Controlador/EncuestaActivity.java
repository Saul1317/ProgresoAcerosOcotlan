package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.R;

public class EncuestaActivity extends AppCompatActivity {

    ImageView btn_feliz_encuesta_pregunta0,btn_contento_encuesta_pregunta0,btn_triste_encuesta_pregunta0,
              btn_feliz_encuesta_pregunta1,btn_contento_encuesta_pregunta1,btn_triste_encuesta_pregunta1,
              btn_feliz_encuesta_pregunta2,btn_contento_encuesta_pregunta2,btn_triste_encuesta_pregunta2,
              btn_feliz_encuesta_pregunta3,btn_contento_encuesta_pregunta3,btn_triste_encuesta_pregunta3,
              img_completado_encuesta_pregunta0,img_completado_encuesta_pregunta1,
              img_completado_encuesta_pregunta2,img_completado_encuesta_pregunta3;

    GridLayout gridlayout_encuesta;
    TextView txt_finalizacion_encuesta,txt_descripcion_encuesta;
    Vibrator v;
    int contadorPreguntas=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        Inicializador();
        btn_feliz_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(1);
            }
        });

        btn_contento_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(1);
            }
        });

        btn_triste_encuesta_pregunta0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(1);
            }
        });

        btn_feliz_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(2);
            }
        });

        btn_contento_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(2);
            }
        });

        btn_triste_encuesta_pregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(2);
            }
        });

        btn_feliz_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EfectoPregunta(3);
            }
        });

        btn_contento_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { EfectoPregunta(3);
            }
        });

        btn_triste_encuesta_pregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { EfectoPregunta(3);
            }
        });

        btn_feliz_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { EfectoPregunta(4);
            }
        });

        btn_contento_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { EfectoPregunta(4);
            }
        });

        btn_triste_encuesta_pregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { EfectoPregunta(4);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_agregar_comentario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EncuestaActivity.this, EncuestaComentario.class);
                startActivity(i);
            }
        });
    }
    private void EfectoPregunta(int pregunta) {
        switch (pregunta){
            case 1:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta0.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta0.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta0.setVisibility(View.GONE);
                img_completado_encuesta_pregunta0.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 2:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta1.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta1.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta1.setVisibility(View.GONE);
                img_completado_encuesta_pregunta1.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 3:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta2.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta2.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta2.setVisibility(View.GONE);
                img_completado_encuesta_pregunta2.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            case 4:
                v.vibrate(200);
                contadorPreguntas=contadorPreguntas+1;
                btn_feliz_encuesta_pregunta3.setVisibility(View.GONE);
                btn_contento_encuesta_pregunta3.setVisibility(View.GONE);
                btn_triste_encuesta_pregunta3.setVisibility(View.GONE);
                img_completado_encuesta_pregunta3.setVisibility(View.VISIBLE);
                ValidarContadorPreguntas();
                break;
            default:
                break;
        }
    }
    private void ValidarContadorPreguntas() {
        if (contadorPreguntas==4){
            gridlayout_encuesta.setVisibility(View.INVISIBLE);
            txt_finalizacion_encuesta.setVisibility(View.VISIBLE);
            txt_descripcion_encuesta.setVisibility(View.INVISIBLE);
        }
    }
    private void Inicializador() {
        v= (Vibrator) getSystemService(VIBRATOR_SERVICE);

        gridlayout_encuesta=(GridLayout) findViewById(R.id.gridlayout_encuesta);
        txt_finalizacion_encuesta=(TextView) findViewById(R.id.text_encuesta_finalizada);
        txt_descripcion_encuesta = (TextView) findViewById(R.id.txt_descripcion_encuesta);

        btn_feliz_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta0);
        btn_contento_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta0);
        btn_triste_encuesta_pregunta0=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta0);

        btn_feliz_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta1);
        btn_contento_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta1);
        btn_triste_encuesta_pregunta1=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta1);

        btn_feliz_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta2);
        btn_contento_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta2);
        btn_triste_encuesta_pregunta2=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta2);

        btn_feliz_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_feliz_encuesta_pregunta3);
        btn_contento_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_contenta_encuesta_pregunta3);
        btn_triste_encuesta_pregunta3=(ImageView) findViewById(R.id.btn_triste_encuesta_pregunta3);

        img_completado_encuesta_pregunta0=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta0);
        img_completado_encuesta_pregunta1=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta1);
        img_completado_encuesta_pregunta2=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta2);
        img_completado_encuesta_pregunta3=(ImageView) findViewById(R.id.img_completado_encuesta_pregunta3);
    }
}
