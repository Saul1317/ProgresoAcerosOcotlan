package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class Estructurales extends AppCompatActivity implements View.OnClickListener{

    Button btn_producto_viga_ipr, btn_producto_viga_ips, btn_producto_canales, btn_producto_angulos,
            btn_producto_perfiles_tubulares_reforzados, btn_producto_monten2, btn_producto_placa_acero;
    ImageView img_estructurales_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estructurales);
        iniciador();
    }

    private void iniciador() {
        btn_producto_viga_ipr = (Button) findViewById(R.id.btn_producto_viga_ipr);
        btn_producto_viga_ipr.setOnClickListener(this);
        btn_producto_viga_ips = (Button) findViewById(R.id.btn_producto_viga_ips);
        btn_producto_viga_ips.setOnClickListener(this);
        btn_producto_canales = (Button) findViewById(R.id.btn_producto_canales);
        btn_producto_canales.setOnClickListener(this);
        btn_producto_angulos = (Button) findViewById(R.id.btn_producto_angulos);
        btn_producto_angulos.setOnClickListener(this);
        btn_producto_perfiles_tubulares_reforzados = (Button) findViewById(R.id.btn_producto_perfiles_tubulares_reforzados);
        btn_producto_perfiles_tubulares_reforzados.setOnClickListener(this);
        btn_producto_monten2 = (Button) findViewById(R.id.btn_producto_monten2);
        btn_producto_monten2.setOnClickListener(this);
        btn_producto_placa_acero = (Button) findViewById(R.id.btn_producto_placa_acero);
        btn_producto_placa_acero.setOnClickListener(this);
        img_estructurales_background = (ImageView) findViewById(R.id.img_estructurales_background);
        //se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_estructurales_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_producto_viga_ipr:
                nextActivity(URLs.URL_ESTRUCTURALES_VIGA_IPR);
                break;

            case R.id.btn_producto_viga_ips:
                nextActivity(URLs.URL_ESTRUCTURALES_VIGA_IPS);
                break;

            case R.id.btn_producto_canales:
                nextActivity(URLs.URL_ESTRUCTURALES_CANALES);
                break;

            case R.id.btn_producto_angulos:
                nextActivity(URLs.URL_ESTRUCTURALES_ANGULOS);
                break;

            case R.id.btn_producto_perfiles_tubulares_reforzados:
                nextActivity(URLs.URL_ESTRUCTURALES_PERFILES_TUBULARES);
                break;

            case R.id.btn_producto_monten2:
                nextActivity(URLs.URL_ESTRUCTURALES_MONTEN);
                break;

            case R.id.btn_producto_placa_acero:
                nextActivity(URLs.URL_ESTRUCTURALES_PLACAS);
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
