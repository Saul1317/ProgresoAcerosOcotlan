package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class Planos extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_placa_acero2, btn_producto_lamina_negra, btn_producto_galvanizados2;
    ImageView img_planos_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos);

        btn_producto_placa_acero2 = (Button) findViewById(R.id.btn_producto_placa_acero2);
        btn_producto_placa_acero2.setOnClickListener(this);

        btn_producto_lamina_negra = (Button) findViewById(R.id.btn_producto_lamina_negra);
        btn_producto_lamina_negra.setOnClickListener(this);

        btn_producto_galvanizados2 = (Button) findViewById(R.id.btn_producto_galvanizados2);
        btn_producto_galvanizados2.setOnClickListener(this);

        img_planos_background = (ImageView) findViewById(R.id.img_planos_background);
        Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(img_planos_background);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_producto_placa_acero2:
                nextActivity("planos/placas_acero.pdf");
                break;

            case R.id.btn_producto_lamina_negra:
                nextActivity("planos/lamina_negra.pdf");
                break;

            case R.id.btn_producto_galvanizados2:
                nextActivity("planos/lamina_galvanizadas.pdf");
                break;

            default:
                break;
        }
    }

    public void nextActivity(String nombre_pdf){
        Intent intent = new Intent(this, CatalogoPDF.class);
        intent.putExtra("url_pdf", nombre_pdf);
        startActivity(intent);
    }
}
