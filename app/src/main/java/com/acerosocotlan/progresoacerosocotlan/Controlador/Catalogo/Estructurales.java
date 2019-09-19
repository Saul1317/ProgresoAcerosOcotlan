package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        //Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(img_estructurales_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_producto_viga_ipr:
                nextActivity("estructurales/viga_ipr.pdf");
                break;

            case R.id.btn_producto_viga_ips:
                nextActivity("estructurales/viga_ips.pdf");
                break;

            case R.id.btn_producto_canales:
                nextActivity("estructurales/canales.pdf");
                break;

            case R.id.btn_producto_angulos:
                nextActivity("estructurales/angulos.pdf");
                break;

            case R.id.btn_producto_perfiles_tubulares_reforzados:
                nextActivity("estructurales/perfiles_tubulares_reforzados.pdf");
                break;

            case R.id.btn_producto_monten2:
                nextActivity("estructurales/monten.pdf");
                break;

            case R.id.btn_producto_placa_acero:
                nextActivity("estructurales/placas_acero.pdf");
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
