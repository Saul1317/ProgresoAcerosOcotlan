package com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.acerosocotlan.progresoacerosocotlan.R;

public class MenuSucursales extends AppCompatActivity implements View.OnClickListener {

    CardView btn_localizar_sucursal, btn_sucursales_disponibles;
    String URL = "http://maps.google.com/maps?q=Aceros+ocotlan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sucursales);
        iniciador();
    }

    private void iniciador() {
        btn_localizar_sucursal = (CardView) findViewById(R.id.btn_localizar_sucursal);
        btn_localizar_sucursal.setOnClickListener(this);
        btn_sucursales_disponibles = (CardView) findViewById(R.id.btn_sucursales_disponibles);
        btn_sucursales_disponibles.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_localizar_sucursal:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
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
