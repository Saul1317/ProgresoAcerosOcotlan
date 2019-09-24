package com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class MenuSucursales extends AppCompatActivity implements View.OnClickListener {

    CardView btn_localizar_sucursal, btn_sucursales_disponibles;
    ImageView fondo_menu_sucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sucursales);
        //Se inicializan los componentes
        iniciador();
    }

    private void iniciador() {
        btn_localizar_sucursal = (CardView) findViewById(R.id.btn_localizar_sucursal);
        btn_localizar_sucursal.setOnClickListener(this);
        btn_sucursales_disponibles = (CardView) findViewById(R.id.btn_sucursales_disponibles);
        btn_sucursales_disponibles.setOnClickListener(this);
        fondo_menu_sucursales = (ImageView) findViewById(R.id.fondo_menu_sucursales);
        //Se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).placeholder(R.drawable.ao_portada_fondo1).into(fondo_menu_sucursales);
    }

    //Evento de los botones
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_localizar_sucursal:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URLs.URL_BUSCAR_SUCURSALES_CERCANAS));
                startActivity(i);
                break;

            case R.id.btn_sucursales_disponibles:
                Intent intent = new Intent(this, SucursalesDisponibles.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
