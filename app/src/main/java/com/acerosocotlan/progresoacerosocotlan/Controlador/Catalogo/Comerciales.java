package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class Comerciales extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_perfiles_comerciales, btn_producto_solera, btn_producto_monten;
    ImageView img_comerciales_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comerciales);
        iniciador();
    }

    public void iniciador(){
        btn_producto_perfiles_comerciales = (Button) findViewById(R.id.btn_producto_perfiles_comerciales);
        btn_producto_perfiles_comerciales.setOnClickListener(this);
        btn_producto_solera = (Button) findViewById(R.id.btn_producto_solera);
        btn_producto_solera.setOnClickListener(this);
        btn_producto_monten = (Button) findViewById(R.id.btn_producto_monten);
        btn_producto_monten.setOnClickListener(this);
        img_comerciales_background = (ImageView) findViewById(R.id.img_comerciales_background);
        //Se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_comerciales_background);
    }

    @Override
    public void onClick(View v) {
        //Según la opción que se seleccione es el pdf que se abrira
        switch (v.getId()){
            case R.id.btn_producto_perfiles_comerciales:
                nextActivity(URLs.URL_COMERCIALES_PERFILES);
                break;
            case R.id.btn_producto_solera:
                nextActivity(URLs.URL_COMERCIALES_SOLERAS);
                break;
            case R.id.btn_producto_monten:
                nextActivity(URLs.URL_COMERCIALES_MONTEN);
                break;
            default:
                break;
        }
    }

    //Se mandará por el intent el tipo de pdf para utilizarlo en SucursalesDisponibles.class
    public void nextActivity(String nombre_pdf){
        Intent intent = new Intent(this, CatalogoPDF.class);
        //por clave y valor se envia la ubicación del pdf
        intent.putExtra("url_pdf", nombre_pdf);
        startActivity(intent);
    }
}
