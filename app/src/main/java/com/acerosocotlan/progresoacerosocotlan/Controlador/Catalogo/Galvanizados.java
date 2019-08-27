package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class Galvanizados extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_galvanizados;
    ImageView img_galvanizados_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galvanizados);
        btn_producto_galvanizados = (Button) findViewById(R.id.btn_producto_galvanizados);
        btn_producto_galvanizados.setOnClickListener(this);
        img_galvanizados_background = (ImageView) findViewById(R.id.img_galvanizados_background);
        Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(img_galvanizados_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_producto_galvanizados:
                nextActivity("galvanizados/galvanizado.pdf");
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
