package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class CatalogoProductos extends AppCompatActivity implements View.OnClickListener {

    Button btn_catalogo_galvanizados, btn_catalogo_comercial, btn_catalogo_perfiles, btn_catalogo_estructurales, btn_catalogo_planos, btn_catalogo_corrugados;
    ImageView img_catalago_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_productos);

        btn_catalogo_galvanizados = (Button) findViewById(R.id.btn_catalogo_galvanizados);
        btn_catalogo_galvanizados.setOnClickListener(this);

        btn_catalogo_comercial = (Button) findViewById(R.id.btn_catalogo_comercial);
        btn_catalogo_comercial.setOnClickListener(this);

        btn_catalogo_perfiles = (Button) findViewById(R.id.btn_catalogo_perfiles);
        btn_catalogo_perfiles.setOnClickListener(this);

        btn_catalogo_estructurales = (Button) findViewById(R.id.btn_catalogo_estructurales);
        btn_catalogo_estructurales.setOnClickListener(this);

        btn_catalogo_planos = (Button) findViewById(R.id.btn_catalogo_planos);
        btn_catalogo_planos.setOnClickListener(this);

        btn_catalogo_corrugados = (Button) findViewById(R.id.btn_catalogo_corrugados);
        btn_catalogo_corrugados.setOnClickListener(this);

        img_catalago_background = (ImageView) findViewById(R.id.img_catalago_background);
        Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(img_catalago_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_catalogo_galvanizados:
                Intent intent = new Intent(this, Galvanizados.class);
                startActivity(intent);
                break;

            case R.id.btn_catalogo_comercial:
                Intent comerciales = new Intent(this, Comerciales.class);
                startActivity(comerciales);
                break;

            case R.id.btn_catalogo_perfiles:
                Intent perfiles = new Intent(this, PerfilesTubulares.class);
                startActivity(perfiles);
                break;

            case R.id.btn_catalogo_estructurales:
                Intent estructurales = new Intent(this, Estructurales.class);
                startActivity(estructurales);
                break;

            case R.id.btn_catalogo_planos:
                Intent planos = new Intent(this, Planos.class);
                startActivity(planos);
                break;

            case R.id.btn_catalogo_corrugados:
                Intent corrugados = new Intent(this, CorrugadosTrefilados.class);
                startActivity(corrugados);
                break;
            default:
                break;
        }
    }



}
