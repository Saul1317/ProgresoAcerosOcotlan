package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.AvisoPrivacidad.AvisoPrivacidad;
import com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor.MenuConvertidor;
import com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.PortalTransaparencia.PortalTransparencia;
import com.acerosocotlan.progresoacerosocotlan.R;

public class MenuOtrosApartados extends AppCompatActivity implements View.OnClickListener {

    CardView btn_conversiones, btn_portal_trasparencia, btn_aviso_privacidad;
    ImageView imagen_fondo_menu_otros_apartados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_otros_apartados);

        imagen_fondo_menu_otros_apartados = (ImageView) findViewById(R.id.imagen_fondo_menu_otros_apartados);
        //Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(imagen_fondo_menu_otros_apartados);

        btn_conversiones = (CardView) findViewById(R.id.btn_conversiones);
        btn_conversiones.setOnClickListener(this);

        btn_portal_trasparencia = (CardView) findViewById(R.id.btn_portal_trasparencia);
        btn_portal_trasparencia.setOnClickListener(this);

        btn_aviso_privacidad = (CardView) findViewById(R.id.btn_aviso_privacidad);
        btn_aviso_privacidad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_conversiones:
                Intent menuConvertidor = new Intent(MenuOtrosApartados.this, MenuConvertidor.class);
                startActivity(menuConvertidor);
                break;

            case R.id.btn_portal_trasparencia:
                Intent portalTransparencia = new Intent(MenuOtrosApartados.this, PortalTransparencia.class);
                startActivity(portalTransparencia);
                break;

            case R.id.btn_aviso_privacidad:
                Intent avisoPrivacidad = new Intent(MenuOtrosApartados.this, AvisoPrivacidad.class);
                startActivity(avisoPrivacidad);
                break;

            default:
                break;
        }
    }


}
