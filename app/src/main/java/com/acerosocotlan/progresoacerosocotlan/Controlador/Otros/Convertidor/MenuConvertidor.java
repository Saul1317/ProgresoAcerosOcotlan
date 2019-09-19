package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class MenuConvertidor extends AppCompatActivity implements View.OnClickListener {

    LinearLayout LinLay_Peso, LinLay_Longitud, LinLay_Temperatura,
            LinLay_Area, LinLay_Presion, LinLay_Potencia, LinLay_Calor, LinLay_Volumen;
    ImageView imagen_fondo_menu_convertidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_convertidor);
        imagen_fondo_menu_convertidor = (ImageView) findViewById(R.id.imagen_fondo_menu_convertidor);
        //Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(imagen_fondo_menu_convertidor);
        LinLay_Peso = (LinearLayout) findViewById(R.id.LinLay_Peso);
        LinLay_Peso.setOnClickListener(this);
        LinLay_Longitud = (LinearLayout) findViewById(R.id.LinLay_Longitud);
        LinLay_Longitud.setOnClickListener(this);
        LinLay_Temperatura = (LinearLayout) findViewById(R.id.LinLay_Temperatura);
        LinLay_Temperatura.setOnClickListener(this);
        LinLay_Area = (LinearLayout) findViewById(R.id.LinLay_Area);
        LinLay_Area.setOnClickListener(this);
        LinLay_Presion = (LinearLayout) findViewById(R.id.LinLay_Presion);
        LinLay_Presion.setOnClickListener(this);
        LinLay_Potencia = (LinearLayout) findViewById(R.id.LinLay_Potencia);
        LinLay_Potencia.setOnClickListener(this);
        LinLay_Calor = (LinearLayout) findViewById(R.id.LinLay_Calor);
        LinLay_Calor.setOnClickListener(this);
        LinLay_Volumen = (LinearLayout) findViewById(R.id.LinLay_Volumen);
        LinLay_Volumen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LinLay_Peso:
                abrirConversor(0);
                break;

            case R.id.LinLay_Longitud:
                abrirConversor(1);
                break;

            case R.id.LinLay_Area:
                abrirConversor(2);
                break;

            case R.id.LinLay_Temperatura:
                abrirConversor(3);
                break;

            case R.id.LinLay_Calor:
                abrirConversor(4);
                break;

            case R.id.LinLay_Potencia:
                abrirConversor(5);
                break;

            case R.id.LinLay_Presion:
                abrirConversor(6);
                break;

            case R.id.LinLay_Volumen:
                abrirConversor(7);
                break;

            default:
                break;
        }
    }


    public void abrirConversor(int tipo_medicion){
        Toast.makeText(MenuConvertidor.this, "Unidad: Peso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MenuConvertidor.this, Convertidor.class);
        intent.putExtra("tipo", tipo_medicion);
        startActivity(intent);
    }
}
