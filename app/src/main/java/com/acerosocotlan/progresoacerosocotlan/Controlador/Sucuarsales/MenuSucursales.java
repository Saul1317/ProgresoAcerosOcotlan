package com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;

public class MenuSucursales extends AppCompatActivity implements View.OnClickListener {

    CardView btn_localizar_sucursal, btn_sucursales_disponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sucursales);
        btn_localizar_sucursal = (CardView) findViewById(R.id.btn_localizar_sucursal);
        btn_localizar_sucursal.setOnClickListener(this);
        btn_sucursales_disponibles = (CardView) findViewById(R.id.btn_sucursales_disponibles);
        btn_sucursales_disponibles.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_localizar_sucursal:
                String map = "http://maps.google.com/maps?q=Aceros+ocotlan";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
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
